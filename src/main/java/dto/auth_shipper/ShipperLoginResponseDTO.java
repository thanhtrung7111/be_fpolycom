package dto.auth_shipper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperLoginResponseDTO {
    String id;
    String userLogin;
    String token;
}
