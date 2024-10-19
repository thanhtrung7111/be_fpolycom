package dto.delivery_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryTypeResponse {
    String name;

    Double fee;

    Long deliveryTypeCode;
}
