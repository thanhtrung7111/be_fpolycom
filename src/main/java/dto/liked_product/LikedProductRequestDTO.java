package dto.liked_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedProductRequestDTO {

    String userLogin;

    Long productCode;

}
