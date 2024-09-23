package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder

@AttributeOverride(name = "id",column = @Column(name = "productDetailCode"))
public class ProductDetail {

    @Lob
    @Nationalized
    String name;

    Double price;

    @Lob
    String image;

    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discountCode")
    Discount discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode")
    Product product;

    @OneToMany(mappedBy = "productDetail")
    List<ShoppingCart> shoppingCartList;


}
