package dto.ward;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WardCreateRequestDTO {

    @NotBlank(message = "Khong de trong ward code")
    String wardCode;

    @NotBlank(message = "Khong de trong thanh pho")
    String districtCode;

    @NotBlank(message = "Khong de trong ten quan")
    String name;


}
