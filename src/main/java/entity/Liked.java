package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AttributeOverride(name="id",column = @Column(name = "likecCode"))
public class Liked extends EntityCommon{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode")
    Product product;

}
