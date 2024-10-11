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


    @Mapping(target = "productDetailCode",source = "productDetail.id")
    @Mapping(target = "discountCode",source = "discount.id")
    OrderDetail toOrderDetail (OrderDetailRequestDTO requestDTO);


    List<OrderDetail> tOrderDetailList(List<OrderDetailRequestDTO> orderDetailRequestDTOList);

}
