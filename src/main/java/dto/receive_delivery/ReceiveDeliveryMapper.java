package dto.receive_delivery;

import entity.Orders;
import entity.ReceiveDelivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReceiveDeliveryMapper {
    ReceiveDeliveryMapper INSTANCE = Mappers.getMapper(ReceiveDeliveryMapper.class);
    @Mapping(target = "orders.id",source = "ordersCode")
    @Mapping(target = "shipper.id", source = "shipperCode")
    @Mapping(target = "id",source = "receiveDeliveryCode")
    ReceiveDelivery toReceiveDelivery(ReceiveDeliveryRequestDTO dto);

    @Mapping(target ="shipperCode",source = "shipper.id")
    @Mapping(target ="ordersCode",source = "orders.id")
    @Mapping(target = "receiveDeliveryCode", source = "id")
    @Mapping(target = "shipperName", source = "shipper.name")
    ReceiveDeliveryResponseDTO toReceiveDeliveryResponseDTO(ReceiveDelivery entity);

    List<ReceiveDeliveryResponseDTO> toReceiveDeliveryResponseDtoList(List<ReceiveDelivery> list);


}
