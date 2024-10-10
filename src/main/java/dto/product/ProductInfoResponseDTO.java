package dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoResponseDTO {

    Long productCode;

    String name;

    String image;

    String status;

    String typeGoodName;

    String typeGoodCode;

    Integer numberOfLikes;

    Integer numberOfEvaluates;

    Double pointEvaluate;

    Double minPrice;

    Double maxPrice;

    Boolean liked;

    List<ProductDetailResponseDTO> productDetailList;
}
