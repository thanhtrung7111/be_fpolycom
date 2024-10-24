package user_controller;

import dto.user_auth.ChangePasswordRequestDTO;
import dto.user_account.UserAccountChangeRequestDTO;
import dto.user_account.UserAccountChangeResponseDTO;
import dto.user_account.UserAccountRegisterResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import service.UserAccountService;
import service.data_return.DataReturnService;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Optional;

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

    @PostMapping("/change-info")
    public ResponseEntity<Object> userChangeInfo(@RequestBody UserAccountChangeRequestDTO requestDTO) throws MessagingException {
        UserAccountChangeResponseDTO result=userAccountService.changeInfomationUser(requestDTO);
        return ResponseEntity.ok().body(dataReturnService.success(result));
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getInfoUser(@RequestBody Optional<HashMap<String,String>> requestDTO) throws MessagingException {
        if(requestDTO.isEmpty() || requestDTO.get().get("userLogin").isBlank()){
            throw new UsernameNotFoundException("Khong ton tai nguoi dung");
        }
        UserAccountChangeResponseDTO result=userAccountService.getInfoUser(requestDTO.get().get("userLogin"));
        return ResponseEntity.ok().body(dataReturnService.success(result));
    }
}
