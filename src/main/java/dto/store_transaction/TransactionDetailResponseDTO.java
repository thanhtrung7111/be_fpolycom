package dto.store_transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailResponseDTO {

    Double totalAmount;

    Long storeTransactionCode;

    String content;

    String bankStoreCode;

    String transactionStatus;

    String typeTransaction;

    String bankAccountName;

    String bankAccountNumber;

    String bankBranchName;

    String bankBranchCode;

    String bankName;
}
