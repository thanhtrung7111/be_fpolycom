package user_controller;

import dto.user_notify.UserNotifycationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.user_notify.UserNotifyService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserNotifycationController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    UserNotifyService userNotifyService;

    @PostMapping(value = "/notify/all")
    public ResponseEntity<Object> getAllNotifyByUser(@RequestBody HashMap<String,String> requestLogin){
        List<UserNotifycationResponseDTO> result = userNotifyService.getAllUserNotifycationByUser(requestLogin.get("userLogin"));
        return ResponseEntity.ok(dataReturnService.success(result));
    }
}
