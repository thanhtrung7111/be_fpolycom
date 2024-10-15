package dto.store_transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminWithdrawResponseDTO {
    Double totalAmount;

    Long storeTransactionCode;

    String content;

    String bankStoreCode;

    String transactionStatus;

    String typeTransaction;
}
