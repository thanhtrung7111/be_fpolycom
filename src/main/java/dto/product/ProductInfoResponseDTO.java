package dto.product;

import dto.product_attr.ProductAttrResponseDTO;
import entity.ProductAttr;
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

    String description;

    String shortDescription;

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

    Long storeCode;

    String storeName;

    List<ProductDetailResponseDTO> productDetailList;

    List<ProductAttrResponseDTO> productAttrList;
}
