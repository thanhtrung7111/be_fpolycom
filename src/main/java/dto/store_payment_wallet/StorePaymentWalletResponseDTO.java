package dto.store_payment_wallet;

import entity.Store;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorePaymentWalletResponseDTO {

    Double balance;

    Boolean setPassword;

    String storeName;

    String storeCode;

}
