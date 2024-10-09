package service.auth_user;

import dao.UserAccountRepository;
import dto.user_auth.AuthUserLoginResponseDTO;
import dto.user_auth.AuthUserMapper;
import entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.common.EncodingService;

@Service
public class AuthUserServiceImpl implements AuthUserService{


    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    EncodingService encodingService;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Override
    public AuthUserLoginResponseDTO getUser(String username) {
        UserAccount userAccount= userAccountRepository.findByUserLogin(username).orElseThrow(()->new UsernameNotFoundException("Không tồn tại nguười dùng!"));
        userAccount.setUserLogin(encodingService.encode(userAccount.getUserLogin()));
        return AuthUserMapper.INSTANCE.toAuthUserLoginResponse(userAccount);
    }

    public String getUserLoginAuthenication() {
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

    @Override
    public String extractUserlogin(String userLogin) {
        return  encodingService.decode(userLogin);
    }

    @Override
    public boolean isValidUserLogin(String rawUserLogin) {
        return extractUserlogin(rawUserLogin).equals(getUserLoginAuthenication());
    }
}
