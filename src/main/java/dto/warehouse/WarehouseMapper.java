package dto.warehouse;

import entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE , componentModel = "spring")
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);
    @Mapping(target = "id",source = "warehouseCode")
    @Mapping(target = "province.id", source = "provinceCode")
    @Mapping(target = "district.id", source = "districtCode")
    @Mapping(target = "ward.id", source = "wardCode")
    Warehouse toWarehouse(WarehouseRequestDTO request);

    @Mapping(target = "provinceCode",source = "province.id")
    @Mapping(target = "districtCode",source = "district.id")
    @Mapping(target = "wardCode",source = "ward.id")
    @Mapping(target = "warehouseCode",source = "id")
    @Mapping(target = "wardName",source = "ward.name")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "provinceName",source = "province.name")
    WarehouseResponseDTO toWarehouseResponseDTO(Warehouse warehouse);

    List<WarehouseResponseDTO> toList(List<Warehouse> list);



    
}
