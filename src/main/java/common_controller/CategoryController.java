package common_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.district.DistrictService;
import service.province.ProvinceService;
import service.ward.WardService;

import java.util.HashMap;

@RestController
@CrossOrigin("*")
public class CategoryController {


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ProvinceService provinceService;


    @Autowired
    DistrictService districtService;

    @Autowired
    WardService wardService;
    @GetMapping(value = "/category/provinces")
    public ResponseEntity<Object> getAllProvince() {
        return ResponseEntity.ok(dataReturnService.success(provinceService.getAllDataCommon()));
    }

    @PostMapping(value = "/category/districts")
    public ResponseEntity<Object> getAllDistrict(@RequestBody HashMap<String,Long> request) {
        return ResponseEntity.ok(dataReturnService.success(districtService.getAllDistrictByProvince(request.get("provinceCode"))));
    }

    @PostMapping(value = "/category/wards")
    public ResponseEntity<Object> getAllWard(@RequestBody HashMap<String,Long> request) {
        return ResponseEntity.ok(dataReturnService.success(wardService.getAllWardByDistrict(request.get("districtCode"))));
    }



}
