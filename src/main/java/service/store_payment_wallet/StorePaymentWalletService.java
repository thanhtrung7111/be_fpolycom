package service.store_payment_wallet;

import dto.store_payment_wallet.StorePaymentWalletResponseDTO;

public interface StorePaymentWalletService {

    public StorePaymentWalletResponseDTO getPaymentByStore(Long storeCode);


    public StorePaymentWalletResponseDTO setPasswordStoreWallet(Long storeCode,String password);

    public StorePaymentWalletResponseDTO loginStoreWallet(Long storeCode,String password);
}
