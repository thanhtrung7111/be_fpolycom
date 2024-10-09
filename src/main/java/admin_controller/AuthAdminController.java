package admin_controller;

import dto.user_account.AdminUserAccountRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserAccountService;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.user_account.AdminUserAccountService;

@RestController
@RequestMapping(value = "/admin")
public class AuthAdminController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    AdminUserAccountService adminUserAccountService;


    @GetMapping("/user-account/all")
    public ResponseEntity<DataReturn> getAllUserAccount() {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.getAll()));
    }
    @PostMapping("/user-account/lock")
    public ResponseEntity<DataReturn> lockUserAccount(@RequestBody AdminUserAccountRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.lockUser(request)));
    }
    @PostMapping("/user-account/unlock")
    public ResponseEntity<DataReturn> unlockUserAccount(@RequestBody AdminUserAccountRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.unlockUser(request)));
    }





}
