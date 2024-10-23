package dto.payment_type;

import entity.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentTypeMapper {

    PaymentTypeMapper INSTANCE = Mappers.getMapper(PaymentTypeMapper.class);

    @Mapping(target = "id",source = "paymentTypeCode")
    PaymentType toPaymentType(PaymentTypeRequestDTO requestDTO);

    @Mapping(target = "paymentTypeCode",source = "id")
    PaymentTypeResponseDTO toPaymentTypeResponseDto(PaymentType paymentType);


    List<PaymentType> toPaymentTypeList(List<PaymentTypeRequestDTO> paymentTypeRequestDTOList);


    List<PaymentTypeResponseDTO> toPaymentTypeResponseDtoList(List<PaymentType> paymentTypeList);
}
