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
@EqualsAndHashCode(callSuper = false)
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

   @OneToMany(mappedBy = "evaluate")
   List<EvaluateImage> evaluateImageList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode")
    Product product;

}
