package dto.auth_user;

import entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AuthUserMapper {

    AuthUserMapper INSTANCE = Mappers.getMapper(AuthUserMapper.class);



    @Mapping(target = "userId",source = "id")
    @Mapping(target = "username",source = "name")
    @Mapping(target = "userImage",source = "image")
    @Mapping(target = "storeStatus",source = "store.storeStatus")
    @Mapping(target = "storeName",source = "store.name")
    AuthUserLoginResponseDTO toAuthUserLoginResponse(UserAccount userAccount);



}
