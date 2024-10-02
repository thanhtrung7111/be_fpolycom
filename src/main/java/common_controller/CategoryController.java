package common_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.province.ProvinceService;

@RestController
@CrossOrigin("*")
public class CategoryController {


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ProvinceService provinceService;

    @GetMapping(value = "/category/provinces")
    public ResponseEntity<Object> getAllProvince() {
        return ResponseEntity.ok(dataReturnService.success(provinceService.getAllDataCommon()));
    }

    @GetMapping(value = "/category/districts")
    public ResponseEntity<Object> getAllDistrict() {
        return ResponseEntity.ok(dataReturnService.success(provinceService.getAllDataCommon()));
    }





}
