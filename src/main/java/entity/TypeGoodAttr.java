package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "typeGoodAttrCode"))
public class TypeGoodAttr extends EntityCommon {

    @Lob
    @Nationalized
    String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeGoodCode")
    TypeGood typeGood;

    @OneToMany(mappedBy = "typeGoodAttr")
    List<ProductAttr> productAttrList;
}
