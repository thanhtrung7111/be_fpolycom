package user_controller;

import dto.authLoginUser.AuthLoginRequest;
import entity.enum_package.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service.JWTService;
import service.UserAccountService;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserAccountService service;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/generateToken")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthLoginRequest authRequest) {
        System.out.println(authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername()+"&"+RoleType.USER.name(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
             return ResponseEntity.ok().body(jwtService.generateToken(authentication.getName()+"&"+ RoleType.USER.name()));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }

    @PostMapping("/generateTokenAdmin")
    public ResponseEntity<Object> authenticateAndGetTokenAdmin(@RequestBody AuthLoginRequest authRequest) {
        System.out.println(authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername()+"&"+RoleType.ADMIN.name(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok().body(jwtService.generateToken(authentication.getName()+"&"+ RoleType.ADMIN.name()));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }

    @PostMapping("/generateTokenStore")
    public ResponseEntity<Object> authenticateAndGetTokenStore(@RequestBody AuthLoginRequest authRequest) {
        System.out.println(authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername()+"&"+RoleType.STORE.name(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok().body(jwtService.generateToken(authentication.getName()+"&"+ RoleType.STORE.name()));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }


    @GetMapping("/auth/user/list")
    public ResponseEntity<Object> getData(){
        return ResponseEntity.ok().body("Success user");
    }

    @GetMapping("/auth/store/list")
    public ResponseEntity<Object> getDataStore(){
        return ResponseEntity.ok().body("Success store");
    }

    @GetMapping("/auth/admin/list")
    public ResponseEntity<Object> getDataAdmin(){
        return ResponseEntity.ok().body("Success admin");
    }

    @GetMapping("/error/access-denied")
    public ResponseEntity<Object> errorDenied(){
        return ResponseEntity.ok().body("Bạn khoong co quyen truy cap vao enpoint này!");
    }


    @GetMapping("/hello")
    public ResponseEntity<Object> getHello(){
        return ResponseEntity.ok().body("Success");
    }

}
