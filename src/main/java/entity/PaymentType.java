package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id",column = @Column(name = "paymentTypeCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class PaymentType extends EntityCommon{

    @Lob
    @Nationalized
    String name;

    @Lob
    String image;

    @OneToMany(mappedBy = "paymentType")
    List<PaymentReceipt> paymentReceiptList;

}
