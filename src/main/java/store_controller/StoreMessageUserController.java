package store_controller;

import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store_group_mess.StoreGroupMessService;

import java.util.HashMap;

@RestController
@RequestMapping("/store")
public class StoreMessageUserController {

    @Autowired
    DataReturnService dataReturnService;


    @Autowired
    StoreGroupMessService storeGroupMessService;

    @PostMapping("/group-message/all")
    public ResponseEntity<Object> getAllGroupMessage(@RequestBody HashMap<String, Long> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        return ResponseEntity.ok(dataReturnService.success(storeGroupMessService.getAllGroupByStore(request.get("storeCode"))));
    }

}
