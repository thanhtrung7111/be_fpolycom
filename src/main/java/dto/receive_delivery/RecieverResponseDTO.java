package dto.receive_delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecieverResponseDTO {
    String id;
    String name;
    String phone;
    String province;
    String district;
    String ward;
    String addressDetail;
    String address;
}
