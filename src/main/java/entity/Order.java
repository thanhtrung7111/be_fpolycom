package entity;

import entity.enum_package.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "orderCode"))
public class Order extends EntityCommon{

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;


    @Temporal(TemporalType.DATE)
    Date deliveryDate;

    @Temporal(TemporalType.DATE)
    Date pickupDate;

    Double totalAmount;

    Double totalAmountVoucher;

    Double totalAmountShip;


}
