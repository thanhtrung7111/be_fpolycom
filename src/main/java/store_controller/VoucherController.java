package store_controller;

import dto.voucher.VoucherRequestDTO;
import dto.voucher.VoucherResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.voucher.VoucherService;

@RestController
@RequestMapping("/store")
public class VoucherController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    VoucherService voucherService;

    @PostMapping(value = "/voucher/all")
    public ResponseEntity<Object> allVouchers(@RequestBody VoucherRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(voucherService.getVoucherByStore(request.getStoreCode())));
    }

    @PostMapping(value = "/voucher/new")
    public ResponseEntity<Object> newVoucher(@RequestBody @Valid VoucherRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(voucherService.postData(request)));
    }

    @PostMapping(value = "/voucher/update")
    public ResponseEntity<Object> updateVoucher(@RequestBody @Valid VoucherRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(voucherService.updateData(request)));
    }

    @PostMapping(value = "/voucher/delete")
    public ResponseEntity<Object> deleteVouchers(@RequestBody VoucherRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(voucherService.deleteData(request)));
    }

}
