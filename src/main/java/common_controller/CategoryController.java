package common_controller;

import dto.authLoginUser.AuthLoginRequest;
import entity.enum_package.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
