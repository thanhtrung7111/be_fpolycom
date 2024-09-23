package entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name="id",column = @Column(name = "bankCode"))
@EqualsAndHashCode(callSuper = false)
@Builder
public class Bank extends EntityCommon{
    @Lob
    @Nationalized
    String name;

    @Lob
    @Nationalized
    String desciption;

    @Lob
    String image;
}
