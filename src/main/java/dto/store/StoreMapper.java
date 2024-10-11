package dto.store;

import entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);


    @Mapping(target = "id", source = "storeRegisterCode")
    @Mapping(target = "province.id", source = "provinceCode")
    @Mapping(target = "district.id", source = "districtCode")
    @Mapping(target = "ward.id", source = "wardCode")
    @Mapping(target = "storeDocumentList",source = "documentList")
    Store toStore(StoreRegisterRequestDTO requestDTO);

    @Mapping(target = "storeRegisterCode", source = "id")
    @Mapping(target = "provinceCode", source = "province.id")
    @Mapping(target = "districtCode", source = "district.id")
    @Mapping(target = "wardCode", source = "ward.id")
    @Mapping(target = "documentList",source = "storeDocumentList")
    @Mapping(target = "userRegister",source = "store.userAccount.name")
    StoreRegisterResponseDTO toStoreRegisterResponseDto(Store store);

    @Mapping(target = "provinceName", source = "province.name")
    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "wardName", source = "ward.name")
    @Mapping(target = "status",source = "storeStatus")
    @Mapping(target = "userRegister",source = "store.userAccount.name")
    @Mapping(target = "numberOfFollowed",source = "followedList",qualifiedByName = "numberOfFollowed")
    @Mapping(target = "numberOfLiked",source = "productList",qualifiedByName = "numberOfLiked")
    UserStoreDetailResponseDTO toUserStoreDetailResponseDto(Store store);



    List<StoreRegisterResponseDTO> toStoreRegisterResponseDtoList(List<Store> storeList);

    List<Store> toStoreList(List<StoreRegisterRequestDTO> storeRegisterRequestDTOList);


    @Named("numberOfFollowed")
    default Integer numberOfFollowed(List<Followed> followeds){
        return  followeds.size();
    }


    @Named("numberOfLiked")
    default Integer numberOfLiked(List<Product> productList){
        return  productList.stream().mapToInt(item->item.getLikedList().size()).sum();
    }
}
