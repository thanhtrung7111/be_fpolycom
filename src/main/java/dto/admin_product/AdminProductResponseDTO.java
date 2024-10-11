package dto.admin_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductResponseDTO {
    Long productCode;

    String name;

    String image;

    String status;

    String typeGoodName;

    String typeGoodCode;

}
