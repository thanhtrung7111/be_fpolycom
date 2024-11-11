package dto.user_notify;

import entity.NotifycationUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserNotifycationMapper {

    UserNotifycationMapper INSTANCE = Mappers.getMapper(UserNotifycationMapper.class);

    @Mapping(target = "notifyUserCode",source = "id")
    UserNotifycationResponseDTO toUserNotifycationResponseDto(NotifycationUser notifycationUser);

    List<UserNotifycationResponseDTO> toUserNotifycationResponseDtoList(List<NotifycationUser> notifycationUserList);

}
