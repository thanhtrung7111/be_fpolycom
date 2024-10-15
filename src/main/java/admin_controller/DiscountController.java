package admin_controller;

import dto.discount.DiscountRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.discount.DiscountService;

@RestController
@RequestMapping("/admin")
public class DiscountController {
    @Autowired
    DiscountService discountService;
    @Autowired
    DataReturnService dataReturnService;

    @GetMapping(value = "/discount/all")
    public ResponseEntity<Object> getAllDiscount() {
        return ResponseEntity.ok(dataReturnService.success(discountService.getAllData()));
    }

    @PostMapping(value = "/discount/new")
    public ResponseEntity<Object> addDiscount(@RequestBody DiscountRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(discountService.postData(request)));
    }

    @PostMapping(value = "/discount/update")
    public ResponseEntity<Object> updateDiscount(@RequestBody DiscountRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(discountService.updateData(request)));
    }

    @PostMapping(value = "/discount/delete")
    public ResponseEntity<Object> deleteDiscount(@RequestBody DiscountRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(discountService.deleteData(request)));
    }
}
