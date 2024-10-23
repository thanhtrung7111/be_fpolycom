package dto.auth_shipper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperLoginResponseDTO {
    String userLogin;
String shipperCode;
    String password;
    String name;
    String token;
}
