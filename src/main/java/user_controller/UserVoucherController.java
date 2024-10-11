package user_controller;

import dto.store_follow.StoreFollowRequestDTO;
import dto.voucher.VoucherRequestDTO;
import dto.voucher_user.VoucherUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.user_voucher.UserVoucherService;

@RestController
@RequestMapping(value = "/user")
public class UserVoucherController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    UserVoucherService userVoucherService;


    @PostMapping(value = "/voucher/all")
    public ResponseEntity<Object> getAllStoreFollow(@RequestBody VoucherUserRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(userVoucherService.getAllVoucherByUser(requestDTO)));
    }

    @PostMapping(value = "/voucher/new")
    public ResponseEntity<Object> postVoucherUser(@RequestBody VoucherUserRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(userVoucherService.postUserVoucher(requestDTO)));
    }

}
