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


    List<OrderDetail> tOrderDetailList(List<OrderDetailRequestDTO> orderDetailRequestDTOList);

}
