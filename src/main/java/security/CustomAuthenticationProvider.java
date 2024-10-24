package security;

import entity.enum_package.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import service.AdministrationService;
import service.ShipperDetailService;
import service.StoreDetailService;
import service.UserAccountService;
import service.auth_user.AuthUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    AdministrationService administrationService;


    @Autowired
    UserAccountService userAccountService;

    @Autowired
    StoreDetailService storeDetailService;

    @Autowired
    ShipperDetailService shipperDetailService;





    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String[] username = authentication.getName().split("&");
        String password = authentication.getCredentials().toString();

        UserDetails userDetails =null;

        // Logic để quyết định sử dụng UserDetailService hay AdminDetailService
        if (username[1].equals(RoleType.USER.name())) {
            userDetails = userAccountService.loadUserByUsername(username[0]); // Lấy thông tin admin
        } else if(username[1].equals(RoleType.ADMIN.name())) {
            userDetails = administrationService.loadUserByUsername(username[0]); // Lấy thông tin user
        }else if(username[1].equals(RoleType.STORE.name())) {
            userDetails = storeDetailService.loadUserByUsername(username[0]); // Lấy thông tin user
        }else if(username[1].equals(RoleType.SHIPPER.name())) {
            userDetails = shipperDetailService.loadUserByUsername(username[0]); // Lấy thông tin shipper
        }

        // Kiểm tra mật khẩu
        if (passwordEncoder().matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Thong tin dang nhap sai!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
