package common_controller;

import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.evaluate.EvaluateService;

import java.util.HashMap;

@RestController
public class CommonController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    EvaluateService evaluateService;

    @PostMapping(value = "/product/evaluate/all")
    public ResponseEntity<Object> getAllProvince(@RequestBody HashMap<String,String> request) {
        if( request.get("productCode") == null || request.get("productCode").isBlank()){
            throw new DataNotFoundException("Khong de trong ma san pham!");
        }
        return ResponseEntity.ok(dataReturnService.success(evaluateService.getAllEvaluateProduct(Long.valueOf(request.get("productCode")))));
    }
}
