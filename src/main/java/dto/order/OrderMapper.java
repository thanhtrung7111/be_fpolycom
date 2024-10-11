package dto.order;

import entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(target = "orderCode",source = "id")
    @Mapping(target = "storeImage",source = "store.image")
    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "deliveryType",source = "deliveryType.name")
    OrderResponseDTO toOrderResponseDto(Orders order);


    List<OrderResponseDTO> toOrderResponseDtoList(List<Orders> ordersList);
}
