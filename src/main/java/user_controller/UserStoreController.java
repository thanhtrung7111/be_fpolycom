package user_controller;

import dto.store.StoreRegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store.StoreService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/user")
public class UserStoreController {


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreService storeService;

    @PostMapping(value = "/store/register")
    public ResponseEntity<Object> storeRegister(@RequestBody StoreRegisterRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(storeService.registerStore(requestDTO)));
    }

}
