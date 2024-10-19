package dto.shipping_fee;

import entity.ShippingFee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShippingFeeMapper {

    ShippingFeeMapper INSTANCE  = Mappers.getMapper(ShippingFeeMapper.class);

    @Mapping(target = "shippingFeeCode",source = "id")
    ShippingFeeResponse toShippingFeeResponse(ShippingFee shippingFee);


    List<ShippingFeeResponse> toShippingFeeResponseList(List<ShippingFee> shippingFeeList);

}
