package admin_controller;

import dto.accept_store.AcceptStoreRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.accept_store.AcceptStoreService;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/admin")
public class AcceptStoreController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    AcceptStoreService acceptStoreService;

    @PostMapping("/store/lock")
    public ResponseEntity<DataReturn> lockStore(@RequestBody AcceptStoreRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(acceptStoreService.lockStore(request)));
    }
    @PostMapping("/store/approve")
    public ResponseEntity<DataReturn> unlockStore(@RequestBody AcceptStoreRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(acceptStoreService.unlockStore(request)));
    }
}
