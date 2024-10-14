package dto.gift;

import dto.order_detail.OrderDetailResponseDTO;
import entity.Gift;
import entity.OrderDetail;
import entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderDetailResponseDTO.class})
public interface GiftMapper {

    GiftMapper INSTANCE = Mappers.getMapper(GiftMapper.class);


    @Mapping(target = "userCodeGift", source = "userAccount.id")
    @Mapping(target = "giftCode", source = "id")
    @Mapping(target = "userCode", source = "orders.userAccount.id")
    @Mapping(target = "dateGift", source = "createdDate")
    @Mapping(target = "usernameGift", source = "userAccount.name")
    @Mapping(target = "username", source = "orders.userAccount.name")
    @Mapping(target = "userImage", source = "orders.userAccount.image")
    @Mapping(target = "userImageGift", source = "userAccount.image")
//    @Mapping(target = "detailOrderList",source = "orders",qualifiedByName = "orderDetails")
    GiftResponseDTO toGiftResponseDto(Gift gift);

    List<GiftResponseDTO> toGiftResponseDtoList(List<Gift> giftList);


    @Named("orderDetails")
    default List<OrderDetail> getAllOrderDetail(Orders orders) {
        return new ArrayList<>(orders.getOrderDetailList());
    }
}
