package dto.admin_store;


import entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AdminStoreMapper {
    AdminStoreMapper INSTANCE = Mappers.getMapper(AdminStoreMapper.class);

    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "district.id",source = "districtCode")
    @Mapping(target = "id", source = "storeID")
    Store toStore(AdminStoreRequestDTO adminStoreRequestDTO);

    @Mapping(target = "storeID", source = "id")
    @Mapping(target = "provinceName",source = "province.name")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "wardName",source = "ward.name")
    AdminStoreResponseDTO toAdminStoreResponseDTO(Store store);

    List<AdminStoreResponseDTO> toList (List<Store> stores);
}
