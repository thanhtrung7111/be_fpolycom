package dto.import_export_orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportExportOrdersRequestDTO {
    Long importExportOrdersCode;

    Long warehouseCode;

    Long ordersCode;



}
