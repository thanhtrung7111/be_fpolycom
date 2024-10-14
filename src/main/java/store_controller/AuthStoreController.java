package store_controller;

import dto.auth_store.AuthStoreLoginRequestDTO;
import dto.auth_store.AuthStoreLoginResponseDTO;
import dto.user_auth.AuthUserLoginRequestDTO;
import dto.user_auth.AuthUserLoginResponseDTO;
import entity.enum_package.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.auth_store.AuthStoreService;
import service.auth_user.AuthUserService;
import service.common.EncodingService;
import service.common.JWTService;
import service.data_return.DataReturnService;

@RestController
public class AuthStoreController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    AuthStoreService authStoreService;

    @Autowired
    EncodingService encodingService;



    @PostMapping("/user/store-login")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthStoreLoginRequestDTO authRequest) {
        System.out.println(authRequest.getUserLogin()+"check controller");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(encodingService.decode(authRequest.getUserLogin())+"&"+ RoleType.STORE.name(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            AuthStoreLoginResponseDTO rs = authStoreService.getStoreByUser(authRequest.getUserLogin());
            System.out.println(rs.getStoreName()+"auth sotre controller");
            String token = jwtService.generateToken(authentication.getName()+"&"+ RoleType.STORE.name());
            rs.setToken(token);
            return ResponseEntity.ok().body(dataReturnService.success(rs));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }
}
