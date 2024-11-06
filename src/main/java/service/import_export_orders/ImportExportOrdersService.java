package service.import_export_orders;

import dto.import_export_orders.ImportExportListOrdersRequestDTO;
import dto.import_export_orders.ImportExportOrdersRequestDTO;
import dto.import_export_orders.ImportExportOrdersResponseDTO;

import java.util.List;

public interface ImportExportOrdersService {
    public ImportExportOrdersResponseDTO confirmImport (Long warehouseCode, Long ordersCode);

    public ImportExportOrdersResponseDTO confirmImportAppoiment (Long warehouseCode, Long ordersCode);

    public ImportExportOrdersResponseDTO confirmExport (Long warehouseCode, Long ordersCode);

    public List<ImportExportOrdersResponseDTO> confirmListImport (ImportExportListOrdersRequestDTO request);

    public List<ImportExportOrdersResponseDTO> confirmListExport (ImportExportListOrdersRequestDTO request);

}
