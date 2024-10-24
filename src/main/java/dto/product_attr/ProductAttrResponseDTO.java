package dto.product_attr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttrResponseDTO {
    String attrValue;

    String typeGoodAttrCode;

    String typeGoodAttrName;

    String productCode;

    String productAttrCode;
}
