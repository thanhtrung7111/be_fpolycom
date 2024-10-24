package dto.user_cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCartResponseDTO {

    String productDetailCode;

    String productName;

    Long productCode;

    Long discountCode;

    Integer percentDecrease;

    String image;

    String detailName;

    Double price;

    Integer quantity;

    String storeName;

    Long provinceCode;

    Long storeCode;
}
