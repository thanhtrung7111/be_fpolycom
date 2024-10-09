package dto.liked_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedProductResponseDTO {

    Long productCode;

    String productName;

    String productImage;

    String typeGoodName;

    String typeGoodCode;

}
