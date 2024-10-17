package service.store_transaction;

import dto.store_transaction.*;
import org.hibernate.Transaction;

import java.util.List;

public interface StoreTransactionService {
    public WithdrawTransactionResponseDTO withdrawTransaction(WithdrawTransactionRequestDTO request);

    public WithdrawTransactionResponseDTO withdrawTransactionCompleted(AdminWithdrawRequestDTO request);

    public WithdrawTransactionResponseDTO withdrawTransactionDeclined(Long code,String content);

    public List<AdminWithdrawResponseDTO> GetAllTransactionIsPending();

    public TransactionDetailResponseDTO getDetailByTransactionCode(Long code);
}
