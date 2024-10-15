package dto.voucher;

import jakarta.validation.constraints.*;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRequestDTO {
    String userLogin;

    Long storeCode;

    Long voucherCode;

    @NotBlank(message = "Phải đặt tên cho voucher")
    String name;

    @NotNull(message = "Giá áp dụng không được để trống!")
    @Positive(message = "Giá áp dụng phải là số dương!")
    Double priceApply;

    @NotNull(message = "Phần trăm giảm giá không được để trống!")
    @Min(value = 1, message = "Phần trăm giảm giá phải lớn hơn hoặc bằng 1")
    @Max(value = 100, message = "Phần trăm giảm giá phải nhỏ hơn hoặc bằng 100")
    Integer percentDecrease;

    String voucherType;

    @Temporal(TemporalType.DATE)
    Date beginDate;

    @Temporal(TemporalType.DATE)
    Date endDate;
}
