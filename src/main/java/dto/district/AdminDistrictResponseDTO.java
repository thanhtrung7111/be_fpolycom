package dto.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDistrictResponseDTO {

    String districtCode;

    String name;

    String provinceCode;



    Integer numberOfWards;

}
