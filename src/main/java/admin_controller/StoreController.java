package admin_controller;

import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.store.StoreService;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value="/admin")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    DataReturnService dataReturnService;

    @GetMapping(value = "/store/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(storeService.getAll()));
    }


    @PostMapping("/store/lock")
    public ResponseEntity<Object> lockStore(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty() || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Cua hang khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(storeService.lockStore(Long.valueOf(request.get("storeCode")))));
    }


    @PostMapping("/store/detail")
    public ResponseEntity<Object> getDetailStore(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty() || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Cua hang khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(storeService.getDetailStore(Long.valueOf(request.get("storeCode")))));
    }


    @PostMapping("/store/approve")
    public ResponseEntity<Object> unlockStore(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty() || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Cua hang khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(storeService.approveStore(Long.valueOf(request.get("storeCode")))));
    }



}
