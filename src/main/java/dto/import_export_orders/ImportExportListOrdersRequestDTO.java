package dto.import_export_orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportExportListOrdersRequestDTO {


    Long warehouseCode;

    List<Long> ordersCode;
}
