package dto.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRequestDTO {
    String discountCode;

    String name;

    String description;

    Date beginDate;




}
