package admin_controller;


import dto.province.ProvinceCreateRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    public ResponseEntity<Object> getNew( @RequestBody ProvinceCreateRequestDTO request,Errors errors) {
        if(errors.hasFieldErrors("name")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("name is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(provinceService.postData(request)));
    }

    @PostMapping(value = "/province/update")
    public ResponseEntity<Object> getUpdate( @RequestBody ProvinceCreateRequestDTO request,Errors errors) {
        if(errors.hasFieldErrors("provinceCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("Provincecode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(provinceService.updateData(request)));
    }



}
