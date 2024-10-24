package store_controller;

import dto.bank_user.BankUserRequestDTO;
import dto.product.ProductMapper;
import dto.product.ProductRequestDTO;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.product.ProductService;

@RestController
@RequestMapping(value = "/store")
public class StoreProductController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ProductService productService;

    @PostMapping(value = "/product/new")
    public ResponseEntity<Object> postNewProduct(@RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(productService.postNew(requestDTO)));
    }

    @PostMapping(value = "/product/update")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(productService.updateData(requestDTO)));
    }


}
