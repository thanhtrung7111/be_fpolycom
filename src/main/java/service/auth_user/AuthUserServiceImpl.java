package service.auth_user;

import dao.UserAccountRepository;
import dto.user_auth.AuthUserLoginResponseDTO;
import dto.user_auth.AuthUserMapper;
import entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.common.EncodingService;

@Service
public class AuthUserServiceImpl implements AuthUserService{


    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    EncodingService encodingService;

    @Override
    public AuthUserLoginResponseDTO getUser(String username) {
        UserAccount userAccount= userAccountRepository.findByUserLogin(username).orElseThrow(()->new UsernameNotFoundException("Không tồn tại nguười dùng!"));
        userAccount.setUserLogin(encodingService.encode(userAccount.getUserLogin()));
        return AuthUserMapper.INSTANCE.toAuthUserLoginResponse(userAccount);
    }
}
