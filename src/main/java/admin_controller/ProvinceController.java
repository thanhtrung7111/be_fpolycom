package admin_controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.province.ProvinceService;

@RequestMapping(value = "/admin")
@RestController
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DataReturnService dataReturnService;

    @GetMapping(value = "/province/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(provinceService.getAllData()));
    }


}
