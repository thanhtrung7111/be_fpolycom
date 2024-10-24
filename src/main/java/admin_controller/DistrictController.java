package admin_controller;

import dto.district.AdminDistrictRequestDTO;
import dto.district.AdminDistrictResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import service.data_return.DataReturnService;
import service.district.DistrictService;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin("*")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @Autowired
    DataReturnService dataReturnService;
    @Autowired
    private View error;

    @GetMapping(value = "/district/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(districtService.getAllData()));
    }

    @PostMapping(value="/district/new")
    public ResponseEntity<Object> getNew(@Valid @RequestBody AdminDistrictRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("name is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(districtService.postData(request)));
    }
    @PostMapping(value="/district/update")
    public ResponseEntity<Object> getUpdate(@RequestBody AdminDistrictRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("districtCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(districtService.updateData(request)));
    }
    @PostMapping(value="/district/delete")
    public ResponseEntity<Object> getDelete(@RequestBody AdminDistrictRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("districtCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(districtService.deleteData(request)));
    }


}
