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
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "voucherCode"))
public class Voucher extends EntityCommon{

    @Lob
    @Nationalized
    String name;

    Double priceApply;
}
