package dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseRequestDTO {
    Long warehouseCode;

    Long provinceCode;

    Long districtCode;

    Long wardCode;

    String address;

    String addressDetail;
}
