package dto.receive_delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignorResponseDTO {
    String id;
    String name;
    String phone;
    String province;
    String district;
    String ward;
    String address;
    String addressDetail;
}
