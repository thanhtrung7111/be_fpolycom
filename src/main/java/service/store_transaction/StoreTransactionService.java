package service.store_transaction;

import dto.store_transaction.AdminWithdrawRequestDTO;
import dto.store_transaction.AdminWithdrawResponseDTO;
import dto.store_transaction.WithdrawTransactionRequestDTO;
import dto.store_transaction.WithdrawTransactionResponseDTO;

import java.util.List;

public interface StoreTransactionService {
    public WithdrawTransactionResponseDTO withdrawTransaction(WithdrawTransactionRequestDTO request);

    public WithdrawTransactionResponseDTO withdrawTransactionCompleted(AdminWithdrawRequestDTO request);

    public List<AdminWithdrawResponseDTO> GetAllTransactionIsPending();
}
