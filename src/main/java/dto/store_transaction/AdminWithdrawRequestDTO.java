package dto.store_transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminWithdrawRequestDTO {
    Long storeTransactionCode;
}
