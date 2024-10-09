package admin_controller;

import dto.product.ProductApproveResponeDTO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        List<ProductApproveResponeDTO> products = productService.getAllData();
        return ResponseEntity.ok(dataReturnService.success(products));
    }
    @PostMapping(value="/product/approve/update")
    public ResponseEntity<Object> updatePendingToActive() {
        List<ProductApproveResponeDTO> updatedProducts = productService.updatePendingProductsToActive();
        return ResponseEntity.ok(dataReturnService.success(updatedProducts));
    }



}
