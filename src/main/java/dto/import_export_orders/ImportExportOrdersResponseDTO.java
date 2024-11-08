package dto.import_export_orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportExportOrdersResponseDTO {
    String warehouseAddressDetail;

    Long warehouseCode;

    Long ordersCode;

    String typeImportExportOrders;

    LocalDateTime date;


}
