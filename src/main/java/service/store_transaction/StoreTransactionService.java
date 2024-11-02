package service.store_transaction;

import dto.store_transaction.*;
import org.hibernate.Transaction;

import java.util.List;

public interface StoreTransactionService {
    public WithdrawTransactionResponseDTO withdrawTransaction(WithdrawTransactionRequestDTO request);

    public AdminWithdrawResponseDTO withdrawTransactionCompleted(AdminWithdrawRequestDTO request);

    public AdminWithdrawResponseDTO withdrawTransactionDeclined(Long code,String content);

    public List<AdminWithdrawResponseDTO> getAllTransactionIsPending();

    public TransactionDetailResponseDTO getDetailByTransactionCode(Long code);
    public List<AdminWithdrawResponseDTO> getAllTransactionByStore(Long storeCode);

}
