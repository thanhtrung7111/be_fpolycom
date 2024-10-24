package store_controller;

import dto.bank_user.BankUserRequestDTO;
import dto.product.ProductMapper;
import dto.product.ProductRequestDTO;
import entity.Product;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.auth_store.AuthStoreService;
import service.data_return.DataReturnService;
import service.product.ProductService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/store")
public class StoreProductController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ProductService productService;


    @Autowired
    AuthStoreService authStoreService;

    @PostMapping(value = "/product/new")
    public ResponseEntity<Object> postNewProduct(@RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(productService.postNew(requestDTO)));
    }

    @PostMapping(value = "/product/update")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(productService.updateData(requestDTO)));
    }

    @PostMapping(value = "/product/detail")
    public ResponseEntity<Object> getDetailProduct(@RequestBody HashMap<String,String> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("productCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.getProductById(Long.valueOf(requestDTO.get("productCode")))));
    }


    @PostMapping(value = "/product/all")
    public ResponseEntity<Object> updateProduct(@RequestBody HashMap<String,String> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("storeCode").isBlank()){
            throw new DataNotFoundException(("Du lieu khong ton tai"));
        }
        if(authStoreService.isValidStore(Long.valueOf(requestDTO.get("storeCode")))){
            throw new DataNotFoundException("Ban khong the thuc hien tren du lieu nay!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.getALlProductByStore(Long.valueOf(requestDTO.get("storeCode")))));
    }


}
