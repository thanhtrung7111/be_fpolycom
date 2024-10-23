package admin_controller;

import dto.district.AdminDistrictRequestDTO;
import dto.payment_type.PaymentTypeRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.payment_type.PaymentTypeService;

@RestController
@RequestMapping(value = "/admin")
public class PaymentTypeController {


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    PaymentTypeService paymentTypeService;

    @GetMapping(value = "/payment-type/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(paymentTypeService.getAllData()));
    }

    @PostMapping(value="/payment-type/new")
    public ResponseEntity<Object> getNew(@RequestBody PaymentTypeRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("name")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("name is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(paymentTypeService.postData(request)));
    }
    @PostMapping(value="/payment-type/update")
    public ResponseEntity<Object> getUpdate(@RequestBody PaymentTypeRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("paymentTypeCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("Payment type code is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(paymentTypeService.updateData(request)));
    }
    @PostMapping(value="/payment-type/delete")
    public ResponseEntity<Object> getDelete(@RequestBody PaymentTypeRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("paymentTypeCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("Payment type code is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(paymentTypeService.deleteData(request)));
    }

}
