package entity;

import entity.enum_package.StatusDelivery;
import entity.enum_package.TypeDelivery;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id", column = @Column(name = "receiveShippingCode"))
public class ReceiveDelivery extends EntityCommon {
    String image;

    @Temporal(TemporalType.DATE)
    Date deliveryDate;

    @Enumerated(EnumType.STRING)
    TypeDelivery typeDelivery;

    @Enumerated(EnumType.STRING)
    StatusDelivery statusDelivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipperCode")
    Shipper shipper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderCode")
    Orders orders;
}
