package dto.voucher_user;

import entity.Voucher;
import entity.VoucherUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoucherUserMapper {

    VoucherUserMapper mapper = Mappers.getMapper(VoucherUserMapper.class);


    @Mapping(target = "voucherName",source ="voucher.name")
    @Mapping(target = "priceApply",source ="voucher.priceApply")
    @Mapping(target = "percentDecrease",source ="voucher.percentDecrease")
    @Mapping(target = "endDate",source ="voucher.endDate")
    @Mapping(target = "storeCode",source ="voucher.store.id")
    @Mapping(target = "voucherType",source ="voucher.voucherType")
    VoucherUserResponseDTO toVoucherUserResponseDto(VoucherUser voucherUser);

    List<VoucherUserResponseDTO> toVoucherUserResponseDtoList(List<VoucherUser> voucherList);

}
