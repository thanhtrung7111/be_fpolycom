package store_controller;

import dao.PaymentWallerStoreRepository;
import dto.store_transaction.WithdrawTransactionRequestDTO;
import entity.PaymentWalletStore;
import exeception_handler.DataNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store_payment_wallet.StorePaymentWalletService;
import service.store_transaction.StoreTransactionService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/store")
public class StoreTransactionController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreTransactionService storeTransactionService;

    @Autowired
    StorePaymentWalletService storePaymentWalletService;


    @PostMapping(value = "/store-wallet/get")
    public ResponseEntity<Object> getInfoPaymentWallet(@RequestBody HashMap<String, Long> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(storePaymentWalletService.getPaymentByStore(request.get("storeCode"))));
    }

    @PostMapping(value = "/store-wallet/setPassword")
    public ResponseEntity<Object> setPasswordWallet(@RequestBody HashMap<String, String> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(storePaymentWalletService.setPasswordStoreWallet(Long.valueOf(request.get("storeCode")), request.get("password"))));
    }

    @PostMapping(value = "/store-wallet/login")
    public ResponseEntity<Object> loginStoreWallet(@RequestBody HashMap<String, String> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(storePaymentWalletService.loginStoreWallet(Long.valueOf(request.get("storeCode")), request.get("password"))));
    }


    @PostMapping(value = "/store-transaction/withdraw")
    public ResponseEntity<Object> withdraw(@RequestBody WithdrawTransactionRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.withdrawTransaction(request)));
    }

    @PostMapping(value = "/store-transaction/all")
    public ResponseEntity<Object> getAllByStore(@RequestBody HashMap<String, Long> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Khong ton tai du lieu!");
        }
        return ResponseEntity.ok(dataReturnService.success(storeTransactionService.getAllTransactionByStore(request.get("storeCode"))));
    }
}
