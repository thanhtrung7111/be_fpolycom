package dto.store_transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminWithdrawResponseDTO {
    Double totalAmount;

    Long storeCode;

    String storeName;

    Long storeTransactionCode;

    String content;

    String bankBranchName;

    String bankName;

    String bankStoreCode;

    String transactionStatus;

    String bankAccountName;

    String bankAccountNumber;

    String typeTransaction;

    Date createDate;
}
