package dto.shipper;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperResponseDTO {
    String shipperCode;

    String userLogin;

    String password;

    String name;

    String address;

    String addressDetail;

    String email;

    String phone;

    String provinceCode;

    String districtCode;

    String wardCode;

    String shipperStatus;
}
