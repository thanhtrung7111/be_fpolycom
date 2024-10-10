package admin_controller;

import dto.bank.BankRequestDTO;
import dto.role_admin.RoleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.role.RoleService;

@RestController
@RequestMapping(value="/admin")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    DataReturnService dataReturnService;
    @GetMapping(value = "/role/all")
    public ResponseEntity<Object> getAllRole() {
        return ResponseEntity.ok(dataReturnService.success(roleService.getAllData()));
    }

    @PostMapping(value = "/role/new")
    public ResponseEntity<Object> newRole(@RequestBody RoleRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name") ){
            return ResponseEntity.ok(dataReturnService.dataNotFound("Rolecode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(roleService.postData(request)));
    }


    @PostMapping(value = "/role/update")
    public ResponseEntity<Object> updateRole(@RequestBody RoleRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("roleCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("Rolecode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(roleService.updateData(request)));
    }

    @PostMapping(value = "/role/delete")
    public ResponseEntity<Object> deleteRole(@RequestBody RoleRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("roleCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("Rolecode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(roleService.deleteData(request)));
    }
}
