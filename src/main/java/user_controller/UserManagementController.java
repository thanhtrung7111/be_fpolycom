package user_controller;

import dto.auth_user.ChangePasswordRequestDTO;
import dto.user_account.UserAccountRegisterResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserAccountService;
import service.data_return.DataReturnService;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping("/change-password")
    public ResponseEntity<Object> userChangePassword(@RequestBody ChangePasswordRequestDTO requestDTO) throws MessagingException {
        UserAccountRegisterResponseDTO result=userAccountService.changePassword(requestDTO);
        return ResponseEntity.ok().body(dataReturnService.success(result));
    }
}
