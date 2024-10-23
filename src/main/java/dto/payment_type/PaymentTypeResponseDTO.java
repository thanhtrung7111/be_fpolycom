package dto.payment_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeResponseDTO {

    Long paymentTypeCode;

    String name;


    String image;

}
