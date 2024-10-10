package common_controller;

import entity.enum_package.ProductStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.evaluate.EvaluateService;
import service.product.ProductService;
import service.voucher.VoucherService;

import java.util.HashMap;

@RestController
public class CommonController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    ProductService productService;

    @PostMapping(value = "/product/evaluate/all")
    public ResponseEntity<Object> getAllProvince(@RequestBody HashMap<String,String> request) {
        if( request.get("productCode") == null || request.get("productCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma san pham!");
        }
        return ResponseEntity.ok(dataReturnService.success(evaluateService.getAllEvaluateProduct(Long.valueOf(request.get("productCode")))));
    }

    @PostMapping(value = "/common/store/all-voucher")
    public ResponseEntity<Object> getALlVoucherByStore(@RequestBody HashMap<String,String> request) {
        if( request.get("storeCode") == null || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(voucherService.getVoucherByStore(Long.valueOf(request.get("storeCode")))));
    }

    @GetMapping(value = "/common/product/all")
    public ResponseEntity<Object> getAllProduct() {
        return ResponseEntity.ok(dataReturnService.success(productService.getALlProductByStatus(ProductStatus.active)));
    }

    @PostMapping(value = "/common/store/all-product")
    public ResponseEntity<Object> getALlProductByStore(@RequestBody HashMap<String,String> request) {
        if( request.get("storeCode") == null || request.get("storeCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.getALlProductByStatus(ProductStatus.active)));
    }

    @PostMapping(value = "/common/product/detail")
    public ResponseEntity<Object> getProductById(@RequestBody HashMap<String,String> request) {
        if( request.get("productCode") == null || request.get("productCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma cua hang!");
        }
        return ResponseEntity.ok(dataReturnService.success(productService.getProductById(Long.valueOf(request.get("productCode")),request.get("userLogin"))));
    }
}
