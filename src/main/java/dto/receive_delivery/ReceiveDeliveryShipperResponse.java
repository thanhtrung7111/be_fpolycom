package dto.receive_delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveDeliveryShipperResponse {
    Long receiveDeliveryCode;
    Date deliveryDate;
    String statusDelivery;
    String orderCode;
    String receiver;
    String address;
}
