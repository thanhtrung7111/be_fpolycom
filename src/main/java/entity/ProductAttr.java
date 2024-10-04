package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "productAttrCode"))
public class ProductAttr extends  EntityCommon{

    @Lob
    @Nationalized
    String attrValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeGoodAttrCode")
    TypeGoodAttr typeGoodAttr;
}
