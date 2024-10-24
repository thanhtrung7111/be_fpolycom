package dto.store_banner;

import entity.Product;
import entity.Store;
import entity.enum_package.BannerPosition;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreBannerRequest {

    Long storeBannerCode;

    BannerPosition bannerPosition;

    String image;

    String title;

    Boolean status;

    Long productCode;

    Long storeCode;
}
