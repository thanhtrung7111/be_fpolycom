package user_controller;

import dto.auth_user.AuthUserLoginRequestDTO;
import dto.auth_user.AuthUserLoginResponseDTO;
import dto.user_account.UserAccountRegisterRequestDTO;
import dto.user_account.UserAccountRegisterResponseDTO;
import entity.enum_package.RoleType;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service.auth_user.AuthUserService;
import service.common.JWTService;
import service.UserAccountService;
import service.common.MailService;
import service.data_return.DataReturnService;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    MailService mailService;


    @PostMapping("/user-login")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthUserLoginRequestDTO authRequest) {
        System.out.println(authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername()+"&"+RoleType.USER.name(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            AuthUserLoginResponseDTO rs = authUserService.getUser(authRequest.getUsername());
            String token = jwtService.generateToken(authentication.getName()+"&"+ RoleType.USER.name());
            rs.setToken(token);
             return ResponseEntity.ok().body(dataReturnService.success(rs));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }

    @PostMapping("/user-register")
    public ResponseEntity<Object> userRegister(@Valid @RequestBody UserAccountRegisterRequestDTO accountRequestDTO) throws MessagingException {
        UserAccountRegisterResponseDTO result=userAccountService.registerAccount(accountRequestDTO);
        mailService.sendMail(result.getEmail(),"Xac nhan dang nhạp","Dang ki thanh cong");
        return ResponseEntity.ok().body(dataReturnService.success(result));
    }

    @GetMapping("/user-confirm/{token}")
    public ResponseEntity<Object> userRegister(@PathVariable("token") String token) throws MessagingException {
        UserAccountRegisterResponseDTO result=userAccountService.confirmAccount(token);
        return ResponseEntity.ok().body(dataReturnService.success(result));
    }



}
