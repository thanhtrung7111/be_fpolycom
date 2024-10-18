package dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProductResponseDTO {

    Long productCode;

    String name;



    String image;

    String status;

    String typeGoodName;

    String typeGoodCode;

    Integer numberOfLikes;

    Integer numberOfEvaluates;

    Long provinceCode;

    String provinceName;

    Double pointEvaluate;

    Double minPrice;

    Double maxPrice;

}
