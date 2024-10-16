package admin_controller;

import dto.store_transaction.AdminWithdrawRequestDTO;
import entity.StoreTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.store_transaction.StoreTransactionService;

@RestController
@RequestMapping(value = "/admin")
public class ManageStoreController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreTransactionService storeTransactionService;

    @PostMapping(value = "/manage-store/completed-withdraw")
    public ResponseEntity<Object> completedWithdraw(@RequestBody AdminWithdrawRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.withdrawTransactionCompleted(request)));
    }

    @GetMapping(value = "/manage-store/all-transaction")
    public ResponseEntity<Object> storeTransaction() {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.GetAllTransactionIsPending()));
    }
}
