package admin_controller;

import dto.administration.AdministrationRequestDTO;
import dto.role_admin.RoleRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.administration.AdministrationService;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value="/admin")
public class AdministrationController {
    @Autowired
    AdministrationService administrationService;
    @Autowired
    DataReturnService dataReturnService;
    @GetMapping(value = "/admin-account/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(administrationService.getAllData()));
    }

    @PostMapping(value = "/admin-account/new")
    public ResponseEntity<Object> newAdmin(@Valid @RequestBody AdministrationRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("userLogin")&&(errors.hasFieldErrors("Password")) ){
            return ResponseEntity.ok(dataReturnService.dataNotFound("userLogin & Password is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(administrationService.postData(request)));
    }


    @PostMapping(value = "/admin-account/update")
    public ResponseEntity<Object> updateAdmin(@RequestBody AdministrationRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("adminCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("adminCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(administrationService.updateData(request)));
    }

    @PostMapping(value = "/admin-account/delete")
    public ResponseEntity<Object> deleteAdmin(@RequestBody AdministrationRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("adminCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("adminCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(administrationService.deleteData(request)));
    }
}
