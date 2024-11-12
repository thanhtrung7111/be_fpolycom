package service.user_notify;

import dao.UserNotifycationRepository;
import dto.user_notify.UserNotifycationMapper;
import dto.user_notify.UserNotifycationRequestDTO;
import dto.user_notify.UserNotifycationResponseDTO;
import entity.NotifycationUser;
import entity.UserAccount;
import entity.enum_package.TypeNotifycationUser;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;

@Service
public class UserNotifyImpl implements UserNotifyService{


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    UserNotifycationRepository userNotifycationRepository;

    @Autowired
    AuthUserService authUserService;
    @Override
    public UserNotifycationResponseDTO postData(UserNotifycationRequestDTO userNotifycationRequestDTO) {
        return null;
    }

    @Override
    public UserNotifycationResponseDTO updateData(UserNotifycationRequestDTO userNotifycationRequestDTO) {
        return null;
    }

    @Override
    public UserNotifycationResponseDTO deleteData(UserNotifycationRequestDTO userNotifycationRequestDTO) {
        return null;
    }

    @Override
    public List<UserNotifycationResponseDTO> getAllData() {
        return null;
    }

    @Override
    public UserNotifycationResponseDTO getDetailData(Long aLong) {
        return null;
    }

    @Override
    public List<UserNotifycationResponseDTO> getAllUserNotifycationByUser(String userLogin) {
        String username = authUserService.extractUserlogin(userLogin);
        System.out.println(username);
        return UserNotifycationMapper.INSTANCE.toUserNotifycationResponseDtoList(userNotifycationRepository.findAllByUser(authUserService.extractUserlogin(userLogin)));
    }

    @Override
    @Async("taskExecutor")
    public void sendNotifyToUser(String title, String content, String linkContent, TypeNotifycationUser typeNotifycationUser,String image, Long userCode) {
        NotifycationUser notifycationUser =NotifycationUser.builder().userAccount(UserAccount.builder().id(userCode).build()).title(title).content(content).linkContent(linkContent).typeNotifycation(typeNotifycationUser).createdDate(new Date()).deleted(false).readed(false).image(image).deletedDate(null).updatedDate(null).build();
        userNotifycationRepository.save(notifycationUser);
        messagingTemplate.convertAndSend("/topic/notifications/"+userCode, UserNotifycationMapper.INSTANCE.toUserNotifycationResponseDto(notifycationUser));
    }

    @Override
    public UserNotifycationResponseDTO updateReaded(Long notifyUserCode) {

        NotifycationUser notifycationUser = userNotifycationRepository.findById(notifyUserCode).orElseThrow(()->new DataNotFoundException("Dữ liệu không tồn tại!"));
        notifycationUser.setReaded(true);
        userNotifycationRepository.save(notifycationUser);
        return UserNotifycationMapper.INSTANCE.toUserNotifycationResponseDto(notifycationUser);
    }
}
