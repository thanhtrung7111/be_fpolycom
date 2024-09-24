package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "paymentReceiptCode"))
public class PaymentReceipt extends EntityCommon {

    Double totalAmount;

    Double totalAmountVoucher;

    Double totalAmountPaid;

    Double finalTotal;

    Double totalAmountShip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentTypeCode")
    PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderCode")
    Orders orders;
}
