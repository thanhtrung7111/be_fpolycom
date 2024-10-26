package dto.voucher;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRespUserDTO {
    Long storeCode;

    Long voucherCode;

    Boolean apply;

    Integer amount;

    String name;

    Double priceApply;

    Integer percentDecrease;

    String voucherType;

    @Temporal(TemporalType.DATE)
    Date beginDate;

    @Temporal(TemporalType.DATE)
    Date endDate;
}
