package dto.receive_delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReceiveDeliveryRequestDTO {
    List<Long> ordersCode;

    Long shipperCode;
}
