package dto.discount;

import entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface DiscountMapper {
    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    @Mapping(target = "id", source = "discountCode")
    Discount toDiscount (DiscountRequestDTO request);

    @Mapping(target = "discountCode", source = "id")
    @Mapping(target = "storeCode", source = "store.id")
    @Mapping(target = "numberOfProductDetail",source = "productDetailList",qualifiedByName = "numberOfProductDetail")
    @Mapping(target = "numberOfOrderDetail",source = "orderDetailList", qualifiedByName = "numberOfOrderDetail")
    DiscountResponseDTO toDiscountResponseDTO(Discount discount);

    List<DiscountResponseDTO> toDiscountResponseDTOList(List<Discount> discounts);

    @Named("numberOfProductDetail")
    default Integer numberOfProduct(List<ProductDetail> details) {
        if (details == null){
            return 0;
        }
        return details.size();
    }

    @Named("numberOfOrderDetail")
    default Integer numberOfOrders(List<OrderDetail> orders) {
        if (orders == null){
            return 0;
        }
        return orders.size();
    }



}
