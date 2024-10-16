package admin_controller;

import dto.auth_admin.AuthAdminRequestDTO;
import dto.auth_admin.AuthAdminResponseDTO;
import dto.auth_store.AuthStoreLoginRequestDTO;
import dto.auth_store.AuthStoreLoginResponseDTO;
import dto.user_account.AdminUserAccountRequestDTO;
import entity.enum_package.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import service.UserAccountService;
import service.auth_admin.AuthAdminService;
import service.common.JWTService;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.user_account.AdminUserAccountService;


@RestController
public class AuthAdminController {


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthAdminService authAdminService;

    @PostMapping("/admin-login")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthAdminRequestDTO authRequest) {
        System.out.println(authRequest.getUserLogin()+"check controller");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserLogin()+"&"+ RoleType.ADMIN.name(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            AuthAdminResponseDTO rs = authAdminService.getAdmin(authRequest.getUserLogin());
            String token = jwtService.generateToken(authentication.getName()+"&"+ RoleType.ADMIN.name());
            rs.setToken(token);
            return ResponseEntity.ok().body(dataReturnService.success(rs));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }

}
