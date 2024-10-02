package admin_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/admin")
public class WardController {

    @Autowired
    DataReturnService dataReturnService;

//    @GetMapping(value = "/ward/all")
//    public ResponseEntity<Object> getAll() {
//        return ResponseEntity.ok(dataReturnService.success(provinceService.getAllData()));
//    }


}
