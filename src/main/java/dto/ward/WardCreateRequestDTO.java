package dto.ward;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WardCreateRequestDTO {

    @NotNull(message = "Khong de trong ward code")
    Long wardCode;

    @NotNull(message = "Khong de trong thanh pho")
    Long districtCode;

    @NotBlank(message = "Khong de trong ten quan")
    String name;


}
