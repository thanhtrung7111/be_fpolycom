package service;

import dao.TokenRegisterRepository;
import dao.UserAccountRepository;
import dto.auth_user.ChangePasswordRequestDTO;
import dto.user_account.UserAccountMapper;
import dto.user_account.UserAccountRegisterRequestDTO;
import dto.user_account.UserAccountRegisterResponseDTO;
import entity.TokenRegister;
import entity.UserAccount;
import entity.enum_package.UserStatus;
import exeception_handler.DataNotFoundException;
import exeception_handler.NotRightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.SecurityConfig;
import security.UserInfoDetails;
import service.common.EncodingService;

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

    @Autowired
    EncodingService encodingService;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userDetail = userAccountRepository.findByUserLogin(username);
        return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("USer not found"));
    }

    public String getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();  // Trong một số trường hợp có thể là chuỗi username trực tiếp
            }
        }
        throw new UsernameNotFoundException("Nguoi dung chua xac thuc!");
    }


    public UserAccountRegisterResponseDTO registerAccount(UserAccountRegisterRequestDTO request) {
        UserAccount userAccount = UserAccountMapper.INSTANCE.toUserAccount(request);
        userAccount.setPassword(new BCryptPasswordEncoder().encode(userAccount.getPassword()));
        userAccount.setUserStatus(UserStatus.pending);
        userAccount.setCreatedDate(new Date());
        UserAccount saved = userAccountRepository.save(userAccount);
        LocalDateTime curentDateTime = LocalDateTime.now();
        LocalDateTime expiredDateTime = curentDateTime.plusMinutes(15);
        TokenRegister tokenRegister = TokenRegister.builder().token(UUID.randomUUID().toString()).userAccount(saved).createdDate(new Date()).expiredDate(expiredDateTime).build();
        tokenRegisterRepository.save(tokenRegister);
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(saved);
    }

    public UserAccountRegisterResponseDTO confirmAccount(String token) {
        UserAccount userAccount = userAccountRepository.findByTokenRegister(token).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng đăng kí!"));
        if (userAccount.getTokenRegister().getExpiredDate().isBefore(LocalDateTime.now())) {
            throw new UsernameNotFoundException("Token đã hết hạn");
        }
        userAccount.setUserStatus(UserStatus.active);
        userAccountRepository.save(userAccount);
        tokenRegisterRepository.delete(userAccount.getTokenRegister());

        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }

    public UserAccountRegisterResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        String username = encodingService.decode(changePasswordRequestDTO.getUserLogin());
        System.out.println(username);
        System.out.println(getUserLogin());
        if (!username.equals(getUserLogin())) {
            throw new NotRightException("Bạn khong có quyền truy cập thao tác treen nguoiw dung nay!");
        }
        UserAccount userAccount = userAccountRepository.findByUserLogin(username).orElseThrow(() -> new UsernameNotFoundException("Khong ton tai nguoi dung!"));


        if (!new BCryptPasswordEncoder().matches( changePasswordRequestDTO.getPasswordCurrent(),userAccount.getPassword())) {
            throw new DataNotFoundException("Mat khau hien tai khong dung!");
        }

        userAccount.setPassword(new BCryptPasswordEncoder().encode(changePasswordRequestDTO.getPasswordNew()));
        userAccountRepository.save(userAccount);
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }


}
