package admin_controller;


import dto.province.ProvinceCreateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/province/new")
    public ResponseEntity<Object> getNew(@RequestBody ProvinceCreateRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(provinceService.postData(request)));
    }

    @PostMapping(value = "/province/update")
    public ResponseEntity<Object> getUpdate(@RequestBody ProvinceCreateRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(provinceService.updateData(request)));
    }

    @PostMapping(value = "/province/delete")
    public ResponseEntity<Object> getDelete(@RequestBody ProvinceCreateRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(provinceService.deleteData(request)));
    }


}
