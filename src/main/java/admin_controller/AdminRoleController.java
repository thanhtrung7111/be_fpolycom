package admin_controller;

import dto.adminrole.AdminRoleRequestDTO;
import dto.role_admin.RoleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.adminrole.AdminRoleService;
import service.data_return.DataReturnService;
import service.role.RoleService;

@RestController
@RequestMapping(value="/admin")
public class AdminRoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    DataReturnService dataReturnService;
    @GetMapping(value = "/adminrole/all")
    public ResponseEntity<Object> getAllRole() {
        return ResponseEntity.ok(dataReturnService.success(adminRoleService.getAllData()));
    }



}
