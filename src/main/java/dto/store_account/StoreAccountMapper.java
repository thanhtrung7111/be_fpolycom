package dto.store_account;

import entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreAccountMapper {
    StoreAccountMapper INSTANCE = Mappers.getMapper(StoreAccountMapper.class);

    @Mapping(target = "id",source = "storeCode")
    Store toStore(ChangeStorePasswordRequestDTO dto);

    @Mapping(target = "storeCode",source = "id")
    @Mapping(target = "provinceCode",source = "province.id")
    @Mapping(target = "districtCode",source = "district.id")
    @Mapping(target = "wardCode",source = "ward.id")
    ChangeStorePasswordResponseDTO changeStorePasswordDTO(Store store);

    @Mapping(target = "id",source = "storeCode")
    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "district.id",source = "districtCode")
    @Mapping(target = "ward.id",source = "wardCode")
    Store toStore(ChangeInfoStoreRequestDTO dto);

    @Mapping(target = "storeCode",source = "id")
    @Mapping(target = "provinceCode",source = "province.id")
    @Mapping(target = "districtCode",source = "district.id")
    @Mapping(target = "wardCode",source = "ward.id")
    ChangeInfoStoreResponseDTO changeInfoStoreDTO(Store store);
}
