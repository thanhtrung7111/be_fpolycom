package dto.voucher;

import entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoucherMapper {

    VoucherMapper INSTANCE = Mappers.getMapper(VoucherMapper.class);


    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "voucherCode",source = "id")
    VoucherResponseDTO toVoucherResponseDto(Voucher voucher);

    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "voucherCode",source = "id")
    VoucherRespUserDTO tVoucherRespUserDto(Voucher voucher);


    @Mapping(target = "store.id", source = "storeCode")
    @Mapping(target = "id",source = "voucherCode")
    Voucher toVoucher (VoucherRequestDTO requestDTO);


    List<Voucher> toVoucherList(List<VoucherRequestDTO> requestDTOList);

    List<VoucherResponseDTO> toVoucherResponseDtoList(List<Voucher> voucherList);

    List<VoucherRespUserDTO> toVoucherRespUserDtoList(List<Voucher> voucherList);
}
