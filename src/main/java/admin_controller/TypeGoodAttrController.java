package admin_controller;


import dto.type_good_attr.TypeGoodAttrRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.type_good_attr.TypeGoodAttrService;

@RestController
@RequestMapping(value = "/admin")
public class TypeGoodAttrController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    TypeGoodAttrService typeGoodAttrService;

    @GetMapping(value = "/typegoodattr/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(typeGoodAttrService.getAllData()));
    }

    @PostMapping(value="/typegoodattr/new")
    public ResponseEntity<Object> getNew(@Valid @RequestBody TypeGoodAttrRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("name is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodAttrService.postData(request)));
    }
    @PostMapping(value="/typegoodattr/update")
    public ResponseEntity<Object> getUpdate(@RequestBody TypeGoodAttrRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("districtCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodAttrService.updateData(request)));
    }
    @PostMapping(value="/typegoodattr/delete")
    public ResponseEntity<Object> getDelete(@RequestBody TypeGoodAttrRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("districtCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodAttrService.deleteData(request)));
    }
}
