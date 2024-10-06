package dto.store_follow;

import entity.Followed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import service.store_follow.StoreFollowService;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface StoreFollowMapper {

    StoreFollowMapper INSTANCE = Mappers.getMapper(StoreFollowMapper.class);

    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "storeImage",source = "store.image")
    StoreFollowResponseDTO toStoreFollowResponseDto(Followed followed);


    List<StoreFollowResponseDTO> toStoreFollowResponseDtoList(List<Followed> followedList);
}
