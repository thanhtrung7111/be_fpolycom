package store_controller;

import dto.store_transaction.WithdrawTransactionRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store_transaction.StoreTransactionService;

@RestController
@RequestMapping(value = "store")
public class StoreTransactionController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreTransactionService storeTransactionService;

    @PostMapping(value = "/store-transaction/withdraw")
    public ResponseEntity<Object> withdraw (@RequestBody @Valid WithdrawTransactionRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.withdrawTransaction(request)));
    }
}
