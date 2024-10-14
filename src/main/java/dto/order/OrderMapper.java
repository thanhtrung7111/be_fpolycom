package dto.order;

import dto.order_detail.OrderDetailMapper;
import dto.order_detail.OrderDetailRequestDTO;
import entity.Orders;
import org.hibernate.query.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,uses = {OrderDetailMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(target = "orderCode",source = "id")
    @Mapping(target = "storeImage",source = "store.image")
    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "deliveryType",source = "deliveryType.name")
    OrderResponseDTO toOrderResponseDto(Orders order);


    @Mapping(target = "shippingFee.id",source ="shippingFeeCode" )
    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "deliveryType.id",source = "deliveryTypeCode")
    @Mapping(target = "orderDetailList",source = "orderDetailList")
    @Mapping(target = "store.id",source = "storeCode")
    Orders toOrders (UserOrderRequestDTO requestDTO);

    @Mapping(target = "shippingFee.id",source ="shippingFeeCode" )
    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "deliveryType.id",source = "deliveryTypeCode")
    @Mapping(target = "orderDetailList",source = "orderDetailList")
    @Mapping(target = "store.id",source = "storeCode")
    OrderInfoResponseDTO toOrderInfoResponseDto (Orders orders);

    List<OrderResponseDTO> toOrderResponseDtoList(List<Orders> ordersList);


    List<Orders> toOrdersList(List<UserOrderRequestDTO> orderRequestDTOList);
}
