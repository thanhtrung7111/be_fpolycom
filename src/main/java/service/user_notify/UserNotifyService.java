package service.user_notify;

import dto.user_notify.UserNotifycationRequestDTO;
import dto.user_notify.UserNotifycationResponseDTO;
import entity.Relationship;
import entity.enum_package.TypeNotifycationUser;
import org.springframework.stereotype.Service;
import service.common.CommonService;

import java.util.List;


public interface UserNotifyService extends CommonService<UserNotifycationRequestDTO, UserNotifycationResponseDTO,Long> {


    List<UserNotifycationResponseDTO> getAllUserNotifycationByUser(String userLogin);


    public void sendAddFriendToUser(Long userCode , Relationship relationship);
    public void sendNotifyToUser(String title, String content, String linkContent, TypeNotifycationUser typeNotifycationUser,String image,Long userCode);

    public UserNotifycationResponseDTO updateReaded(Long notifyUserCode);
}
