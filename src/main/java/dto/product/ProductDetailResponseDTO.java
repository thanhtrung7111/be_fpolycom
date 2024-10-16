package dto.product;

import entity.Discount;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseDTO {

    Long productDetailCode;

    String name;

    Double price;

    String image;

    Integer quantity;

    Long discountCode;

    Integer percentDecrease;

}
