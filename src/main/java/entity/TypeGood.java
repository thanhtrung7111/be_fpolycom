package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id",column = @Column(name = "typeGoodCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class TypeGood extends EntityCommon{

    @Lob
    @Nationalized
    String name;

    @OneToMany(mappedBy = "typeGood")
    List<Product> productList;
}
