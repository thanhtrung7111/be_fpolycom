package dto.import_export_orders;

import entity.ImportExportOrders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ImportExportOrdersMapper {
    ImportExportOrdersMapper INSTANCE = Mappers.getMapper(ImportExportOrdersMapper.class);

    @Mapping(target = "id",source = "importExportOrdersCode")
    @Mapping(target = "warehouse.id",source = "warehouseCode")
    @Mapping(target = "orders.id",source = "ordersCode")
    ImportExportOrders toEntity(ImportExportOrdersRequestDTO request);


    ImportExportOrdersResponseDTO toResponse(ImportExportOrders importExportOrders);


}
