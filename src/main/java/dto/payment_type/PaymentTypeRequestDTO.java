package dto.payment_type;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeRequestDTO {

    Long paymentTypeCode;

    String name;


    String image;
}
