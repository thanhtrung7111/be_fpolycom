package dto.order;

import dto.order_detail.OrderDetailResponseDTO;
import dto.order_detail.OrderDetailShipperResponseDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;
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
@NoArgsConstructor
@AllArgsConstructor
public class OrderShipperResponseDTO {
    OrderStatus orderStatus;
    String orderCode;
    String phone;
    String name;
    String addressDetail;
    String address;
    Double finalTotal;
    String noteContent;
    Double shippingFee;
    String store;
    Date orderDate;
    String province;
    String district;
    String ward;
    String deliveryType;
    String paymentType;
    Boolean paymentSuccess;
    List<OrderDetailShipperResponseDTO> orderDetailShipperList;
    List<ReceiveDeliveryResponseDTO> receiveDeliveryList;
}
