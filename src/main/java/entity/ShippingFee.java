package entity;

import entity.enum_package.TypeShipping;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "shippingFeeCode"))
public class ShippingFee extends EntityCommon{

    Double fee;

    TypeShipping typeShipping;

    @OneToMany(mappedBy = "shippingFee")
    List<Orders> ordersList;
}
