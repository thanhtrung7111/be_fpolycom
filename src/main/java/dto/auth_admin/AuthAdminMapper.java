package dto.auth_admin;

import entity.Administration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthAdminMapper {

    AuthAdminMapper INSTANCE  = Mappers.getMapper(AuthAdminMapper.class);


    @Mapping(target = "username",source = "name")
    @Mapping(target = "userImage",source = "image")
    AuthAdminResponseDTO toAuthAdminResponseDto(Administration administration);


}
