package service.store_follow;

import dao.FollowedRepository;
import dto.store_follow.StoreFollowMapper;
import dto.store_follow.StoreFollowRequestDTO;
import dto.store_follow.StoreFollowResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.List;

@Service
public class StoreFollowServiceImpl implements StoreFollowService {

    @Autowired
    FollowedRepository followedRepository;

    @Autowired
    AuthUserService authUserService;

    @Override
    public List<StoreFollowResponseDTO> getAllStoreFollowed(StoreFollowRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        return StoreFollowMapper.INSTANCE.toStoreFollowResponseDtoList(followedRepository.findAllByUserLogin(userLoginExtract));
    }
}
