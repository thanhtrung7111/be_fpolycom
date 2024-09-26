package dto.province;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProvinceCreateRequestDTO {
    String name;

    String provinceCode;
}
