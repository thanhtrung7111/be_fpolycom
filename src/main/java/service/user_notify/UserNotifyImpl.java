package service.user_notify;

import dao.UserNotifycationRepository;
import dto.user_notify.UserNotifycationMapper;
import dto.user_notify.UserNotifycationRequestDTO;
import dto.user_notify.UserNotifycationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.List;

@Service
public class UserNotifyImpl implements UserNotifyService{

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
}
