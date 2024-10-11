package dto.admin_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductRequestDTO {
    Long productCode;

    String name;

    String image;

    String status;

    String store;

    String typeGoodName;

    String typeGoodCode;

    Integer numberOfEvaluates;

    Double pointEvaluate;


}
