package dto.store_transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawTransactionRequestDTO {

    @Min(value = 1000, message = "Phai chuyen khoan it nhat la 1000")
    Double totalAmount;

    @NotNull(message = "khong dc de trong noi dung")
    String content;

    @NotNull (message = "phai lien ket ngan hang truoc khi rut tien")
    String bankStoreCode;

    @NotNull
    Long storeTransactionCode;




}
