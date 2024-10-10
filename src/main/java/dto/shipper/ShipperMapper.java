package dto.shipper;

import dto.role_admin.RoleMapper;
import dto.role_admin.RoleRequestDTO;
import dto.role_admin.RoleResponseDTO;
import entity.Role;
import entity.Shipper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,componentModel = "spring" )
public interface ShipperMapper {
    ShipperMapper INSTANCE = Mappers.getMapper(ShipperMapper.class);
    // Mapping từ DTO sang Entity
    @Mapping(target = "id", source = "shipperCode")
    Shipper toShipper(ShipperRequestDTO dto);

    // Mapping từ Entity sang DTO
    @Mapping(target = "shipperCode", source = "id")
    ShipperResponseDTO toShipperResponseDto(Shipper shipper);


    List<ShipperResponseDTO> toShipperResponseDTO(List<Shipper> shippers);
}
