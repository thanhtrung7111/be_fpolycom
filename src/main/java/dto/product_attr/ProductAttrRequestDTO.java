package dto.product_attr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttrRequestDTO {
    String attrValue;

    String typeGoodAttrCode;

    String productCode;

    String productAttrCode;

}
