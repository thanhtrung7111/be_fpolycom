package admin_controller;

import dto.type_good.TypeGoodRequestDTO;
import dto.type_good_attr.TypeGoodAttrRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.type_good.TypeGoodService;
import service.type_good_attr.TypeGoodAttrService;

@RestController
@RequestMapping(value = "/admin")
public class TypeGoodController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    TypeGoodService typeGoodService;

    @GetMapping(value = "/typegood/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(typeGoodService.getAllData()));
    }
    @PostMapping(value="/typegood/new")
    public ResponseEntity<Object> getNew(@Valid @RequestBody TypeGoodRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("name is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodService.postData(request)));
    }
    @PostMapping(value="/typegood/update")
    public ResponseEntity<Object> getUpdate(@RequestBody TypeGoodRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("typeGoodCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodService.updateData(request)));
    }
    @PostMapping(value="/typegood/delete")
    public ResponseEntity<Object> getDelete(@RequestBody TypeGoodRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("typeGoodCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(typeGoodService.deleteData(request)));
    }
}
