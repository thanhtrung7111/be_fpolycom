package dto.auth_shipper;

import dto.shipper.ShipperMapper;
import dto.shipper.ShipperRequestDTO;
import dto.shipper.ShipperResponseDTO;
import entity.Shipper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,componentModel = "spring" )

public interface ShipperLoginMapper {
    ShipperLoginMapper INSTANCE = Mappers.getMapper(ShipperLoginMapper.class);


    // Mapping từ Entity sang DTO
    @Mapping(target = "shipperCode", source = "id")
    @Mapping(target = "userLogin", source = "shipper.userLogin")
    @Mapping(target = "password", source = "shipper.password")
    @Mapping(target = "name", source = "shipper.name")

    ShipperLoginResponseDTO toShipperLoginResponseDto(Shipper shipper);


}
