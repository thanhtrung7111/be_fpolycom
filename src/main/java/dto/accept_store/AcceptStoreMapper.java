package dto.accept_store;

import dto.user_account.AdminUserAccountResponseDTO;
import entity.Store;
import entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AcceptStoreMapper {
    AcceptStoreMapper INSTANCE = Mappers.getMapper(AcceptStoreMapper.class);

    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "district.id",source = "districtCode")
    @Mapping(target = "id", source = "userAccountID")
    Store toStore(AcceptStoreRequestDTO acceptStoreRequestDTO);

    @Mapping(target = "userAccountID", source = "id")
    @Mapping(target = "provinceName",source = "province.name")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "wardName",source = "ward.name")
    AcceptStoreResponseDTO toAcceptStoreResponseDTO(Store store);

    List<AcceptStoreResponseDTO> toList (List<Store> stores);
}
