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
    ShipperLoginResponseDTO toShipperLoginResponseDto(Shipper shipper);


}
