package dto.store_banner;

import entity.enum_package.BannerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreBannerResponse {

    Long storeBannerCode;

    String image;

    String title;
    String typeGoodName;


    Boolean status;

    Long productCode;

    String productName;

    Long storeCode;

    String storeName;
    BannerPosition bannerPosition;

}
