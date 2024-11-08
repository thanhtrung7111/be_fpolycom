package service.import_export_orders;

import dto.import_export_orders.ImportExportOrdersRequestDTO;
import dto.import_export_orders.ImportExportOrdersResponseDTO;

public interface ImportExportOrdersService {
    public ImportExportOrdersResponseDTO confirmImport (Long warehouseCode, Long ordersCode);

    public ImportExportOrdersResponseDTO confirmImportAppoiment (Long warehouseCode, Long ordersCode);

    public ImportExportOrdersResponseDTO confirmExport (Long warehouseCode, Long ordersCode);

}
