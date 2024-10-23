package dto.adminrole;

import dto.district.AdminDistrictRequestDTO;
import dto.district.AdminDistrictResponseDTO;
import dto.district.DistrictMapper;
import dto.role_admin.RoleResponseDTO;
import entity.AdminRole;
import entity.District;
import entity.Role;
import entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AdminRoleMapper {
    AdminRoleMapper INSTANCE = Mappers.getMapper(AdminRoleMapper.class);

    @Mapping(target = "id", source = "roleCode")
    @Mapping(target = "administration.id",source = "adminCode")
    AdminRole toEntity(AdminRoleRequestDTO dto);

    @Mapping(target = "roleCode", source = "id")
    @Mapping(target = "adminCode",source = "administration.id")
    AdminRoleResponseDTO toAdminRoleResponseDto(AdminRole entity);

    List<AdminRoleResponseDTO> toAdminRoleResponseDtoList(List<AdminRole> adminRoles);
}
