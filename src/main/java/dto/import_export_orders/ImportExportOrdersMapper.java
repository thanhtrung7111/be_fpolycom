package dto.import_export_orders;

import entity.ImportExportOrders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ImportExportOrdersMapper {
    ImportExportOrdersMapper INSTANCE = Mappers.getMapper(ImportExportOrdersMapper.class);

    @Mapping(target = "id",source = "importExportOrdersCode")
    @Mapping(target = "warehouse.id",source = "warehouseCode")
    @Mapping(target = "orders.id",source = "ordersCode")
    ImportExportOrders toEntity(ImportExportOrdersRequestDTO request);


    @Mapping(target = "ordersCode",source = "orders.id")
    @Mapping(target = "warehouseCode",source = "warehouse.id")
    @Mapping(target = "warehouseAddressDetail",source = "warehouse.addressDetail")
    ImportExportOrdersResponseDTO toResponse(ImportExportOrders importExportOrders);

    List<ImportExportOrdersResponseDTO> toResponseList(List<ImportExportOrders> list);


}
