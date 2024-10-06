package service.user_notify;

import dto.user_notify.UserNotifycationRequestDTO;
import dto.user_notify.UserNotifycationResponseDTO;
import org.springframework.stereotype.Service;
import service.common.CommonService;

import java.util.List;


public interface UserNotifyService extends CommonService<UserNotifycationRequestDTO, UserNotifycationResponseDTO,Long> {


    List<UserNotifycationResponseDTO> getAllUserNotifycationByUser(String userLogin);


}
