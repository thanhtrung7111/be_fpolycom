package dto.user_cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCartRequestDTO {

    String userLogin;

    Long productDetailCode;

    Integer quantity;

}
