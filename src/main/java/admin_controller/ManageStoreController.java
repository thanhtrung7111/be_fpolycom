package admin_controller;

import dto.store_transaction.AdminWithdrawRequestDTO;
import entity.StoreTransaction;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.store_transaction.StoreTransactionService;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
public class ManageStoreController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreTransactionService storeTransactionService;

    @PostMapping(value = "/store-transaction/completed")
    public ResponseEntity<Object> completedWithdraw(@RequestBody AdminWithdrawRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.withdrawTransactionCompleted(request)));
    }

    @PostMapping(value = "/store-transaction/declined")
    public ResponseEntity<Object> declinedWithdrawal(@RequestBody Optional<HashMap<String,String>> request) {
        if(request.isEmpty() || request.get().get("storeTransactionCode").isBlank()){
            throw new DataNotFoundException("Du lieu bi trong!");
        }
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.withdrawTransactionDeclined(Long.valueOf(request.get().get("storeTransactionCode")),"Rut tien khong thanh cong!")));
    }

    @GetMapping(value = "/store-transaction/all")
    public ResponseEntity<Object> storeTransaction() {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.getAllTransactionIsPending()));
    }

    @PostMapping(value = "/store-transaction/detail")
    public ResponseEntity<Object> storeTransaction(@RequestBody AdminWithdrawRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.getDetailByTransactionCode(request.getStoreTransactionCode())));
    }
}
