package dto.order_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDTO {

    Long orderDetailCode;
    Double totalAmount;

    Double totalDiscount;

    Double finalTotal;

    Integer quantity;

    Long productDetailCode;

    Long discountCode;
    Integer percentDecrease;

    String image;

    String productDetailName;

    String productName;

    Double productDetailPrice;

}
