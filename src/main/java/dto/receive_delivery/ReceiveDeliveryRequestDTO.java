package dto.receive_delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveDeliveryRequestDTO {
    Long shipperCode;

    Long wardCode;
}
