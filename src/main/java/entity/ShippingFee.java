package entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCodeBegin")
    Province provinceBegin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCodeEnd")
    Province provinceEnd;


    @OneToMany(mappedBy = "shippingFee")
    List<Orders> ordersList;

}
