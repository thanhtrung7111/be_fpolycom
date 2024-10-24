package store_controller;

import dto.store_account.ChangeInfoStoreRequestDTO;
import dto.store_account.ChangeStorePasswordRequestDTO;
import dto.store_account.ChangeStorePasswordResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store.StoreService;

import javax.xml.crypto.Data;

@RestController
@RequestMapping(value = "/store")
public class StoreManagementController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreService storeService;

    @PostMapping("/change-info")
    public ResponseEntity<Object> changeInfo(@RequestBody @Valid ChangeInfoStoreRequestDTO request){
        return ResponseEntity.ok(dataReturnService.success(storeService.changeInfoStore(request)));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody @Valid ChangeStorePasswordRequestDTO request){
        return ResponseEntity.ok(dataReturnService.success(storeService.changeStorePassword(request)));
    }
}
