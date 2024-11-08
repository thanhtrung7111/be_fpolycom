package dto.store_productSale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSaleResponeDTO {
    Long productCode;
    String name;
    Double price;
    Integer quantitySold;
    Long storeCode;
    String storeName;
    Long typeGoodCode;
    String typeGoodName;
}
