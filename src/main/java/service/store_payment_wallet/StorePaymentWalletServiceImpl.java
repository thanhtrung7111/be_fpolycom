package service.store_payment_wallet;

import dao.PaymentWallerStoreRepository;
import dto.store_payment_wallet.StorePaymentWalletMapper;
import dto.store_payment_wallet.StorePaymentWalletResponseDTO;
import entity.PaymentWalletStore;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StorePaymentWalletServiceImpl implements StorePaymentWalletService{

    @Autowired
    PaymentWallerStoreRepository paymentWallerStoreRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public StorePaymentWalletResponseDTO getPaymentByStore(Long storeCode) {
        return StorePaymentWalletMapper.INSTANCE.toStorePaymentWalletResponseDto(paymentWallerStoreRepository.findPaymentWalletByStore(storeCode).orElseThrow(()-> new DataNotFoundException("Khong ton tai du lieu!")));
    }

    @Override
    public StorePaymentWalletResponseDTO setPasswordStoreWallet(Long storeCode, String password) {

        PaymentWalletStore paymentWalletStore = paymentWallerStoreRepository.findPaymentWalletByStore(storeCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        paymentWalletStore.setPassword(passwordEncoder.encode(password));
        paymentWallerStoreRepository.save(paymentWalletStore);
        return StorePaymentWalletMapper.INSTANCE.toStorePaymentWalletResponseDto(paymentWalletStore);
    }

    @Override
    public StorePaymentWalletResponseDTO loginStoreWallet(Long storeCode, String password) {
        PaymentWalletStore paymentWalletStore = paymentWallerStoreRepository.findPaymentWalletByStore(storeCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        if(!passwordEncoder.matches(password,paymentWalletStore.getPassword())){
            throw new DataNotFoundException("Mat khau khong dung!");
        }
        return StorePaymentWalletMapper.INSTANCE.toStorePaymentWalletResponseDto(paymentWalletStore);
    }
}
