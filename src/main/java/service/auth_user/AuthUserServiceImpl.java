package service.auth_user;

import dao.UserAccountRepository;
import dto.auth_user.AuthUserLoginResponseDTO;
import dto.auth_user.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService{


    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public AuthUserLoginResponseDTO getUser(String username) {
        return AuthUserMapper.INSTANCE.toAuthUserLoginResponse(userAccountRepository.findByUserLogin(username).orElseThrow(()->new UsernameNotFoundException("Không tồn tại nguười dùng!")));
    }
}
