package dto.district;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDistrictRequestDTO {

    Long districtCode;

    @NotBlank(message = "Khong de trong ten")
    String name;
    @NotBlank(message = "Khong de trong thanh pho / tinh")
    String provinceCode;

}
