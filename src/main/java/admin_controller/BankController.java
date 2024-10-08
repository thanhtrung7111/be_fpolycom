package admin_controller;

import dao.BankRepository;
import dto.bank.BankRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.bank.BankService;
import service.data_return.DataReturnService;

@RestController
@RequestMapping(value = "/admin")
public class BankController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    BankService bankService;

    @GetMapping(value = "/bank/all")
    public ResponseEntity<Object> getAllBank() {
        return ResponseEntity.ok(dataReturnService.success(bankService.getAllData()));
    }

    @PostMapping(value = "/bank/new")
    public ResponseEntity<Object> newBank(@RequestBody BankRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name") && errors.hasFieldErrors("shortName")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("districtCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(bankService.postData(request)));
    }


    @PostMapping(value = "/bank/update")
    public ResponseEntity<Object> updateBank(@RequestBody BankRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("bankCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("bankCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(bankService.updateData(request)));
    }

    @PostMapping(value = "/bank/delete")
    public ResponseEntity<Object> deleteBank(@RequestBody BankRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("bankCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("bankCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(bankService.deleteData(request)));
    }
}
