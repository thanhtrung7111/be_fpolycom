package dto.receive_delivery;

import dao.UserAccountRepository;
import entity.Orders;
import entity.ReceiveDelivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserAccountRepository.class})
public interface ReceiveDeliveryMapper {

    ReceiveDeliveryMapper INSTANCE = Mappers.getMapper(ReceiveDeliveryMapper.class);

    @Mapping(target = "receiveDeliveryCode", source = "id")
    @Mapping(target = "deliveryDate", source = "deliveryDate")
    @Mapping(target = "statusDelivery", source = "statusDelivery")
    @Mapping(target = "orderCode", source = "orders.id")
    @Mapping(target = "receiver", source = "orders.userAccount.name")
    @Mapping(target = "address", source = "orders.address")
    ReceiveDeliveryShipperResponse toReceiveDeliveryShipperResponse(ReceiveDelivery dto);
    default List<ReceiveDeliveryShipperResponse> toReceiveDeliveryShipperResponseList(List<ReceiveDelivery> dtoList) {
        return dtoList.stream()
                .map(this::toReceiveDeliveryShipperResponse)
                .collect(Collectors.toList());
    }

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