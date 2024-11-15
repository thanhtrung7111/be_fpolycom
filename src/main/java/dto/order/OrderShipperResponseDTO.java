package dto.order;

import dto.order_detail.OrderDetailResponseDTO;
import dto.order_detail.OrderDetailShipperResponseDTO;
import dto.receive_delivery.ConsignorResponseDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;
import dto.receive_delivery.RecieverResponseDTO;
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
    Double finalTotal;
    String noteContent;
    Double shippingFee;
    Date orderDate;
    String deliveryType;
    String paymentType;
    Boolean paymentSuccess;
    RecieverResponseDTO userAccountInfo;
    ConsignorResponseDTO storeInfo;
    List<OrderDetailShipperResponseDTO> orderDetailShipperList;
    List<ReceiveDeliveryResponseDTO> receiveDeliveryList;
}
