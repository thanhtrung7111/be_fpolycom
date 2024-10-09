package user_controller;

import dto.bank_user.BankUserRequestDTO;
import dto.user_cart.UserCartRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.user_bank.UserBankService;

@RestController
@RequestMapping(value = "/user")
public class UserBankController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    UserBankService userBankService;


    @PostMapping(value = "/bank/all")
    public ResponseEntity<Object> getAllCartByUser(@RequestBody BankUserRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(userBankService.getAllBankByUser(requestDTO)));
    }

    @PostMapping(value = "/bank/new")
    public ResponseEntity<Object> postBankUser(@RequestBody BankUserRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(userBankService.postBankUser(requestDTO)));
    }

}
