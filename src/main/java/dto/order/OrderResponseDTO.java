package dto.order;

import entity.enum_package.OrderStatus;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {


    String orderCode;

    String storeName;

    String storeImage;

    OrderStatus orderStatus;

    @Temporal(TemporalType.DATE)
    Date pickupDate;

    @Temporal(TemporalType.DATE)
    Date deliveryDate;

    Double totalAmount;

    String paymentTypeName;

    Double totalAmountVoucher;

    Double totalAmountShip;

    Double finalTotal;

    String deliveryType;

    Boolean paymentSuccess;
}
