package dto.province;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminProvinceResponseDTO {

    String name;

    String provinceCode;

    Integer numberOfDistricts;



}
