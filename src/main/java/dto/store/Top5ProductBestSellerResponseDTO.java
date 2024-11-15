package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top5ProductBestSellerResponseDTO {
    String name;

    String typeGoodName;

    Long numberOfOrders;

    Double revenueOfProduct;
}
