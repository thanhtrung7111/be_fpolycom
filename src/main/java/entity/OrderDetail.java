package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "orderDetailCode"))
public class OrderDetail extends  EntityCommon{

    Double totalAmount;

    Double totalDiscount;

    Double finalTotal;

    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderCode")
    Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productDetailCode")
    ProductDetail productDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discountCode")
    Discount discount;
}
