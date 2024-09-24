package entity;

import jakarta.persistence.*;
import lombok.*;
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
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "productDetailCode"))
public class ProductDetail extends EntityCommon{

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

    @OneToMany(mappedBy = "productDetail")
    List<OrderDetail> orderDetailList;

}
