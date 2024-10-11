package admin_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.admin_store.AdminStoreService;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/admin")
public class AdminStoreController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    AdminStoreService adminStoreService;

    @GetMapping(value = "/store/all")
    public ResponseEntity<Object> getAllBank() {
        return ResponseEntity.ok(dataReturnService.success(adminStoreService.getAllStore()));
    }
}
