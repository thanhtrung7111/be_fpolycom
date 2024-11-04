package dto.order_detail;

import entity.OrderDetail;
import entity.Orders;
import org.hibernate.query.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper {

    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);


    @Mapping(target = "productDetail.id",source = "productDetailCode")
    @Mapping(target = "discount.id",source = "discountCode")
    OrderDetail toOrderDetail (OrderDetailRequestDTO requestDTO);


    @Mapping(target = "productDetailCode",source = "productDetail.id")
    @Mapping(target = "discountCode",source = "discount.id")
    @Mapping(target = "percentDecrease",source = "discount.percentDecrease")
    @Mapping(target = "orderDetailCode",source = "id")
    @Mapping(target = "image",source = "productDetail.image")
    @Mapping(target = "productDetailName",source = "productDetail.name")
    @Mapping(target = "productDetailPrice",source = "productDetail.price")
    @Mapping(target = "productName",source = "productDetail.product.name")
    OrderDetailResponseDTO toOrderDetailResponseDto(OrderDetail requestDTO);


    List<OrderDetail> tOrderDetailList(List<OrderDetailRequestDTO> orderDetailRequestDTOList);


    List<OrderDetailResponseDTO> toDetailResponseDtoList(List<OrderDetail> orderDetailList);
}
