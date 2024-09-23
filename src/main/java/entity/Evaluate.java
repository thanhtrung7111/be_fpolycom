package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "evaluateCode"))
public class Evaluate extends EntityCommon{

    @Lob
    @Nationalized
    String title;

    @Lob
    @Nationalized
    String content;

    Integer quality;

    @Lob
    String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode")
    Product product;

}
