package dto.user_account;

import entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);


    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "district.id",source = "districtCode")
    UserAccount toUserAccount(UserAccountRegisterRequestDTO userAccountRegisterRequestDTO);


    @Mapping(target = "provinceName",source = "province.name")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "wardName",source = "ward.name")
    UserAccountRegisterResponseDTO toUserAccountRegisterResponseDto(UserAccount userAccount);


}
