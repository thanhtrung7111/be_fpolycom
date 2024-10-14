package dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailRequestDTO {

    Long productDetailCode;

    String name;

    Double price;

    String image;

    Integer quantity;

    Long discountCode;

}
