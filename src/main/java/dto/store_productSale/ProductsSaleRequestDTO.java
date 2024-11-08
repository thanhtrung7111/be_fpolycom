package dto.store_productSale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSaleRequestDTO {
    Long storeCode;
    String typeGoodName;
}
