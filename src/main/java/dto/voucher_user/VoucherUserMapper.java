package dto.voucher_user;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoucherUserMapper {

    VoucherUserMapper mapper = Mappers.getMapper(VoucherUserMapper.class);


}
