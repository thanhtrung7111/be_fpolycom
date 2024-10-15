package dto.user_account;

import entity.UserAccount;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import service.common.EncodingService;

import java.util.List;

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

    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "district.id",source = "districtCode")
    UserAccount toUserAccountFromChangeRequest(UserAccountChangeRequestDTO requestDTO);

    @Mapping(target = "provinceCode",source = "province.id")
    @Mapping(target = "districtCode",source = "district.id")
    @Mapping(target = "wardCode",source = "ward.id")
    @Mapping(target = "provinceName",source = "province.name")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "wardName",source = "ward.name")
    UserAccountChangeResponseDTO toUserAccountChangeResponseDto(UserAccount userAccount);

    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "district.id",source = "districtCode")
    @Mapping(target = "id", source = "userAccountID")
    UserAccount toAdminUserAccount(AdminUserAccountRequestDTO adminUserAccountRequestDTO);


    @Mapping(target = "userAccountID", source = "id")
    @Mapping(target = "provinceName",source = "province.name")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "wardName",source = "ward.name")
    AdminUserAccountResponseDTO toAdminUserAccountResponseDto(UserAccount userAccount);



    List<AdminUserAccountResponseDTO> toList (List<UserAccount> userAccounts);


}
