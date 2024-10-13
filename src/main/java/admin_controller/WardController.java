package admin_controller;

import dao.WardRepository;
import dto.district.AdminDistrictRequestDTO;
import dto.ward.WardCreateRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.ward.WardService;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin("*")
public class WardController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    WardService wardService;

    @GetMapping(value = "/ward/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(wardService.getAllData()));
    }
    @PostMapping(value = "/ward/new")
    public ResponseEntity<Object> newWard(@RequestBody WardCreateRequestDTO wardCreateRequestDTO , Errors errors) {
        if(errors.hasFieldErrors("name")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("name is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(wardService.postData(wardCreateRequestDTO)));
    }
    @PostMapping(value="/ward/update")
    public ResponseEntity<Object> getUpdate(@RequestBody WardCreateRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("wardCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("wardCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(wardService.updateData(request)));
    }
    @PostMapping(value="/ward/delete")
    public ResponseEntity<Object> getDelete(@RequestBody WardCreateRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("wardCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("wardCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(wardService.deleteData(request)));
    }


}
