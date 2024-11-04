package dto.order;

import dto.voucher.VoucherResponseDTO;
import entity.enum_package.OrderStatus;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {


    String orderCode;

    String storeName;

    String storeImage;

    OrderStatus orderStatus;

    Boolean confirmOrder;

    Boolean confirmDelivery;

    Boolean confirmPickup;

    @Temporal(TemporalType.DATE)
    Date pickupDate;

    @Temporal(TemporalType.DATE)
    Date deliveryDate;

    Double totalAmount;

    String paymentTypeName;

    Double totalAmountVoucher;

    Double totalAmountShip;

    Double finalTotal;

    Date orderDate;

    String deliveryType;

    Boolean paymentSuccess;


}
