package service;

import dao.TokenRegisterRepository;
import dao.UserAccountRepository;
import dto.user_account.UserAccountMapper;
import dto.user_account.UserAccountRegisterRequestDTO;
import dto.user_account.UserAccountRegisterResponseDTO;
import entity.TokenRegister;
import entity.UserAccount;
import entity.enum_package.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;


    @Autowired
    TokenRegisterRepository tokenRegisterRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userDetail = userAccountRepository.findByUserLogin(username);
        return userDetail.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("USer not found"));
    }


    public UserAccountRegisterResponseDTO registerAccount(UserAccountRegisterRequestDTO request){
        UserAccount userAccount = UserAccountMapper.INSTANCE.toUserAccount(request);
        userAccount.setPassword(new BCryptPasswordEncoder().encode(userAccount.getPassword()));
        userAccount.setUserStatus(UserStatus.pending);
        userAccount.setCreatedDate(new Date());
        UserAccount saved = userAccountRepository.save(userAccount);
        LocalDateTime curentDateTime  = LocalDateTime.now();
        LocalDateTime expiredDateTime = curentDateTime.plusMinutes(15);
        TokenRegister tokenRegister = TokenRegister.builder().token(UUID.randomUUID().toString()).userAccount(saved).createdDate(new Date()).expiredDate(expiredDateTime).build();
        tokenRegisterRepository.save(tokenRegister);
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(saved);
    }

    public UserAccountRegisterResponseDTO confirmAccount(String token){
        UserAccount userAccount = userAccountRepository.findByTokenRegister(token).orElseThrow(()->new UsernameNotFoundException("Không tìm thấy người dùng đăng kí!"));
        if(userAccount.getTokenRegister().getExpiredDate().isBefore(LocalDateTime.now())){
            throw new UsernameNotFoundException("Token đã hết hạn");
        }
        userAccount.setUserStatus(UserStatus.active);
        userAccountRepository.save(userAccount);
        tokenRegisterRepository.delete(userAccount.getTokenRegister());

        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }


}
