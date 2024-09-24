package user_controller;

import dto.authLoginUser.AuthLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private AuthenticationManager authenticationManager;
    @PostMapping("/generateToken")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthLoginRequest authRequest) {
        System.out.println(authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
             return ResponseEntity.ok().body(jwtService.generateToken(authentication.getName()));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }

    @GetMapping("/auth/user/list")
    public ResponseEntity<Object> getData(){
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/hello")
    public ResponseEntity<Object> getHello(){
        return ResponseEntity.ok().body("Success");
    }

}
