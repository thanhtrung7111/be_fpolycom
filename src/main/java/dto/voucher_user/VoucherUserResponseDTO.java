package dto.voucher_user;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherUserResponseDTO {


    String voucherName;

    Double priceApply;

    Integer percentDecrease;

    Date endDate;

    Long storeCode;

    Long voucherCode;

    String voucherType;
}
