package dto.role_admin;

import dto.product.ProductApproveRequestDTO;
import dto.product.ProductApproveResponeDTO;
import dto.product.ProductMapper;
import entity.Product;
import entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper( unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,componentModel = "spring" )
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    // Mapping từ DTO sang Entity
    @Mapping(target = "id", source = "roleCode")
    Role toRole(RoleRequestDTO dto);

    // Mapping từ Entity sang DTO
    @Mapping(target = "roleCode", source = "id")
    RoleResponseDTO toRoleResponseDto(Role role);


    List<RoleResponseDTO> toRoleResponseDTO(List<Role> roles);
}
