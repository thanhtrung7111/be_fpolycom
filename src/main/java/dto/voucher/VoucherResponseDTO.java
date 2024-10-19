package dto.voucher;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherResponseDTO {

    Long storeCode;

    Long voucherCode;

    String name;

    Double priceApply;

    Integer percentDecrease;

    String voucherType;

    @Temporal(TemporalType.DATE)
    Date beginDate;

    @Temporal(TemporalType.DATE)
    Date endDate;
}
