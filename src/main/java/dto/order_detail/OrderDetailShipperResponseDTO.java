package dto.order_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailShipperResponseDTO {
    Long orderDetailCode;
    Double finalTotal;
    Integer quantity;
    String image;
    String productName;
    String productDetailName;
}
