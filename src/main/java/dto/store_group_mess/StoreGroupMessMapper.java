package dto.store_group_mess;

import entity.GroupMessageStore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreGroupMessMapper {

    StoreGroupMessMapper INSTANCE = Mappers.getMapper(StoreGroupMessMapper.class);


    @Mapping(target = "userCode",source = "userAccount.id")
    @Mapping(target = "userName",source = "userAccount.name")
    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "storeImage",source = "store.image")
    @Mapping(target = "userImage",source = "userAccount.image")
    @Mapping(target = "groupCode",source = "id")
    StoreGroupMessResponseDTO toStoreGroupMessResponseDto(GroupMessageStore groupMessageStore);


    @Mapping(target = "id",source = "groupCode")
    @Mapping(target = "store.id",source = "storeCode")
    @Mapping(target = "userAccount.id",source = "userCode")
    GroupMessageStore toGroupMessageStore(StoreGroupMessRequestDTO requestDTO);


    List<StoreGroupMessResponseDTO> toStoreGroupMessResponseDtoList(List<GroupMessageStore> groupMessageStoreList);
}
