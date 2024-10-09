package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id",column = @Column(name = "evaluateImageCode"))
public class EvaluateImage extends EntityCommon{

    @Lob
    String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluateCode")
    Evaluate evaluate;

}
