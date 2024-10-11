package admin_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.admin_product.AdminProductService;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/admin")
public class AdminProductController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    AdminProductService adminProductService;

    @GetMapping(value = "/product/all")
    public ResponseEntity<Object> getAllBank() {
        return ResponseEntity.ok(dataReturnService.success(adminProductService.getAllProduct()));
    }
}
