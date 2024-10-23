package service.store_transaction;

import dao.StoreTransactionReceiptRepository;
import dao.StoreTransactionRepository;
import dto.store_transaction.*;
import entity.StoreTransaction;
import entity.StoreTransactionReceipt;
import entity.enum_package.TransactionStatus;
import entity.enum_package.TypeTransaction;
import exeception_handler.DataNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StoreTransactionServiceImpl implements StoreTransactionService {
    @Autowired
    StoreTransactionRepository storeTransactionRepository;



    @Autowired
    StoreTransactionReceiptRepository storeTransactionReceiptRepository;

    @Override
    public WithdrawTransactionResponseDTO withdrawTransaction(WithdrawTransactionRequestDTO request) {
        if(request.getBankStoreCode().isBlank()) throw new ValidationException("Hay lien ket ngan hang truoc khi rut tien");
        StoreTransaction storeTransaction = StoreTransactionMapper.INSTANCE.toStoreTransaction(request);
        storeTransaction.setTypeTransaction(TypeTransaction.withdraw);
        storeTransaction.setTransactionStatus(TransactionStatus.pending);
        storeTransaction.setCreatedDate(new Date());
        return StoreTransactionMapper.INSTANCE.toWithdrawTransactionResponseDTO(storeTransactionRepository.save(storeTransaction));
    }

    @Override
    public AdminWithdrawResponseDTO withdrawTransactionCompleted(AdminWithdrawRequestDTO request) {
        StoreTransaction storeTransaction = storeTransactionRepository.findById(request.getStoreTransactionCode()).orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu!"));
        if(storeTransaction.getStoreTransactionReceipt()!=null){
            throw new UsernameNotFoundException("Giao dich da hoan tat khong the xac nhan!");
        }
        storeTransaction.setTransactionStatus(TransactionStatus.complete);
        StoreTransaction saved = storeTransactionRepository.save(storeTransaction);
        StoreTransactionReceipt storeTransactionReceipt = StoreTransactionReceipt.builder().totalAmount(storeTransaction.getTotalAmount()).storeTransaction(saved).createdDate(new Date()).build();
        storeTransactionReceiptRepository.save(storeTransactionReceipt);
        return StoreTransactionMapper.INSTANCE.adminToAdminWithdrawResponseDTO(saved);
    }

    @Override
    public AdminWithdrawResponseDTO withdrawTransactionDeclined(Long code,String content) {
        StoreTransaction storeTransaction = storeTransactionRepository.findById(code).orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu!"));
        storeTransaction.setTransactionStatus(TransactionStatus.failed);
        storeTransaction.setContent(content);
        return StoreTransactionMapper.INSTANCE.adminToAdminWithdrawResponseDTO(storeTransactionRepository.save(storeTransaction));
    }

    @Override
    public List<AdminWithdrawResponseDTO> getAllTransactionIsPending() {
        return StoreTransactionMapper.INSTANCE.toListAdminWithdrawResponseDtoList(storeTransactionRepository.findAll());
    }

    @Override
    public TransactionDetailResponseDTO getDetailByTransactionCode(Long code) {
        return StoreTransactionMapper.INSTANCE.toTransactionDetailResponseDto(storeTransactionRepository.findById(code).orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu!")));
    }
}
