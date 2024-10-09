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

    String detailName;

    Double price;

    Integer quantity;

}
