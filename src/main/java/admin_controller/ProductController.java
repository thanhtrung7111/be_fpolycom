package admin_controller;

import dto.product.ProductApproveRequestDTO;
import dto.product.ProductApproveResponeDTO;
import dto.user_account.AdminUserAccountRequestDTO;
import entity.Product;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.product.ProductService;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<DataReturn> lockProduct(@RequestBody Optional<HashMap<String,String>> request) {
        if(request.isEmpty() || request.get().get("productCode").isBlank()){
            throw new DataNotFoundException("Khong ton tai du lieu!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.lockProduct(Long.valueOf(request.get().get("productCode")))));
    }
    @PostMapping("/product/approve/unlock")
    public ResponseEntity<DataReturn> unlockProduct(@RequestBody Optional<HashMap<String,String>> request) {
        if(request.isEmpty() || request.get().get("productCode").isBlank()){
            throw new DataNotFoundException("Khong ton tai du lieu!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.unlockProduct(Long.valueOf(request.get().get("productCode")))));
    }


}
