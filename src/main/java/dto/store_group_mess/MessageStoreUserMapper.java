package dto.store_group_mess;

import entity.MessageStoreUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageStoreUserMapper {

    MessageStoreUserMapper INSTANCE = Mappers.getMapper(MessageStoreUserMapper.class);


    @Mapping(target = "groupMessageCode",source = "groupMessageStore.id")
    @Mapping(target = "messageCode",source = "id")
    MessageStoreUserResponseDTO toMessageStoreUserResponseDto(MessageStoreUser messageStoreUser);

    @Mapping(target = "groupMessageStore.id",source = "groupMessageCode")
    @Mapping(target = "id",source = "messageCode")
    MessageStoreUser toMessageStoreUser(MessageStoreUserRequestDTO requestDTO);

    List<MessageStoreUserResponseDTO> toMessageStoreUserResponseDtoList(List<MessageStoreUser> messageStoreUserList);
}
