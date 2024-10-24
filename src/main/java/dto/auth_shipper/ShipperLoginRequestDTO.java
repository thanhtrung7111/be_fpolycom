package dto.auth_shipper;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperLoginRequestDTO {
    @NotBlank(message = "Khong de trong userLogin")
    String userLogin;

    @NotBlank(message = "Khong de trong password")
    String password;
}
