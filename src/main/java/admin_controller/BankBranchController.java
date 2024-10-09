package admin_controller;

import dto.bank.BankRequestDTO;
import dto.bank_branch.BankBranchRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.bank.BankService;
import service.bank_branch.BankBranchService;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/admin")

public class BankBranchController {
    @Autowired
    DataReturnService dataService;

    @Autowired
    BankBranchService bankBranchService;



    private final DataReturnService dataReturnService;

    public BankBranchController(DataReturnService dataReturnService) {
        this.dataReturnService = dataReturnService;
    }

    @GetMapping(value = "/bankbranch/all")
    public ResponseEntity<Object> getAllBank() {
        return ResponseEntity.ok(dataReturnService.success(bankBranchService.getAllData()));
    }

    @PostMapping(value = "/bankbranch/new")
    public ResponseEntity<Object> newBank(@RequestBody BankBranchRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name") && errors.hasFieldErrors("shortName")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(bankBranchService.postData(request)));
    }


    @PostMapping(value = "/bankbranch/update")
    public ResponseEntity<Object> updateBank(@RequestBody BankBranchRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("bankBranchCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("bankCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(bankBranchService.updateData(request)));
    }

    @PostMapping(value = "/bankbranch/delete")
    public ResponseEntity<Object> deleteBank(@RequestBody BankBranchRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("bankBranchCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("bankCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(bankBranchService.deleteData(request)));
    }
}
