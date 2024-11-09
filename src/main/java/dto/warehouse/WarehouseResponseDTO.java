package dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponseDTO {
    Long warehouseCode;

    Long provinceCode;

    Long districtCode;

    Long wardCode;

    String provinceName;

    String wardName;

    String districtName;

    String address;

    String addressDetail;

}
