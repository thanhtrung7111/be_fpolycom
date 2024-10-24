package dto.delivery_type;

import entity.DeliveryType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryTypeMapper {

    DeliveryTypeMapper INSTANCE = Mappers.getMapper(DeliveryTypeMapper.class);


    @Mapping(target = "deliveryTypeCode",source = "id")
    DeliveryTypeResponse toDeliveryTypeResponse(DeliveryType deliveryType);

    List<DeliveryTypeResponse> toDeliveryTypeResponseList(List<DeliveryType> deliveryTypeList);
}
