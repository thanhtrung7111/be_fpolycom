package admin_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.ward.WardService;

@RestController
@RequestMapping(value = "/admin")
public class WardController {

    @Autowired
    DataReturnService dataReturnService;
//
//    @Autowired
//    WardService wardService;
//
//    @GetMapping(value = "/ward/all")
//    public ResponseEntity<Object> getAll() {
//        return ResponseEntity.ok(dataReturnService.success(provinceService.getAllData()));
//    }


}
