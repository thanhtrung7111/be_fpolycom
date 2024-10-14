package dto.product;

import dto.product_attr.ProductAttrRequestDTO;
import entity.*;
import entity.enum_package.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    Long productCode;

    String name;

    String description;

    String shortDescription;

    String image;

   Long typeGoodCode;

   Long storeCode;

   List<ProductDetailRequestDTO> productDetailList;

    List<ProductAttrRequestDTO> productAttrList;
}
