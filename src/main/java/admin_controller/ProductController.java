package admin_controller;

import dto.product.ProductApproveRequestDTO;
import dto.product.ProductApproveResponeDTO;
import dto.user_account.AdminUserAccountRequestDTO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.product.ProductService;

import javax.swing.text.View;
import java.util.List;

@RestController
@RequestMapping(value="/admin")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    DataReturnService dataReturnService;


    @GetMapping(value = "product/approve/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(productService.getAll()));
    }

    @PostMapping("/product/approve/lock")
    public ResponseEntity<DataReturn> lockProduct(@RequestBody ProductApproveRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(productService.lockProduct(request)));
    }
    @PostMapping("/product/approve/unlock")
    public ResponseEntity<DataReturn> unlockProduct(@RequestBody ProductApproveRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(productService.unlockProduct(request)));
    }


}
