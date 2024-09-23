package entity;

import entity.enum_package.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "productCode"))
public class Product extends EntityCommon{

    @Lob
    @Nationalized
    String name;

    @Lob
    @Nationalized
    String description;

    @Lob
    @Nationalized
    String shortDescription;

    @Lob
    String image;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeGoodCode")
    TypeGood typeGood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    Store store;

}
