package user_controller;

import dto.store_follow.StoreFollowRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/user")
public class UserVoucherController {

    @Autowired
    DataReturnService dataReturnService;



//    @PostMapping(value = "/voucher/all")
//    public ResponseEntity<Object> getAllStoreFollow(@RequestBody StoreFollowRequestDTO requestDTO){
//        return ResponseEntity.ok(dataReturnService.success(storeFollowService.getAllStoreFollowed(requestDTO)));
//    }

}
