package user_controller;

import dto.store.StoreRegisterRequestDTO;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store.StoreService;
import service.store_document.StoreDocumentService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/user")
public class UserStoreController {


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreService storeService;

    @Autowired
    StoreDocumentService storeDocumentService;

    @PostMapping(value = "/store/register")
    public ResponseEntity<Object> storeRegister(@RequestBody StoreRegisterRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(storeService.registerStore(requestDTO)));
    }

    @PostMapping(value = "/store/update-register")
    public ResponseEntity<Object> updateStoreRegister(@RequestBody StoreRegisterRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(storeService.updateRegisterStore(requestDTO)));
    }

    @PostMapping(value = "/store/get-register")
    public ResponseEntity<Object> getStoreRegister(@RequestBody HashMap<String,String>  requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("userLogin").isBlank() || requestDTO.get("storeCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        return ResponseEntity.ok(dataReturnService.success(storeService.getRegisterStore(requestDTO.get("userLogin"),Long.valueOf(requestDTO.get("storeCode")))));
    }

    @PostMapping(value = "/store/remove-store-document")
    public ResponseEntity<Object> removeStoreDocument(@RequestBody HashMap<String,String>  requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("storeDocumentCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        return ResponseEntity.ok(dataReturnService.success(storeDocumentService.removeData(Long.valueOf(requestDTO.get("storeDocumentCode")))));
    }


}
