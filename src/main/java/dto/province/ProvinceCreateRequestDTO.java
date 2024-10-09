package dto.province;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProvinceCreateRequestDTO {

    @NotBlank(message = "Truong du lieu name bi trong!")
    String name;

    @NotBlank(message = "Truong du lieu provinceCode bi trong!")
    String provinceCode;
}
