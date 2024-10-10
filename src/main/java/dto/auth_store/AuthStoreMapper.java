package dto.auth_store;

import entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthStoreMapper {

    AuthStoreMapper INSTANCE  = Mappers.getMapper(AuthStoreMapper.class);

    @Mapping(target = "storeName",source = "name")
    @Mapping(target = "storeCode",source = "id")
    @Mapping(target = "storeImage",source = "image")
    @Mapping(target = "status",source = "storeStatus")
    AuthStoreLoginResponseDTO toAuthStoreLoginResponseDto(Store store);

}
