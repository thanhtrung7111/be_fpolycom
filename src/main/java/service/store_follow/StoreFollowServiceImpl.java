package service.store_follow;

import dao.FollowedRepository;
import dao.StoreRepository;
import dao.UserAccountRepository;
import dto.store_follow.StoreFollowMapper;
import dto.store_follow.StoreFollowRequestDTO;
import dto.store_follow.StoreFollowResponseDTO;
import entity.Followed;
import entity.Store;
import entity.UserAccount;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;

@Service
public class StoreFollowServiceImpl implements StoreFollowService {

    @Autowired
    FollowedRepository followedRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<StoreFollowResponseDTO> getAllStoreFollowed(StoreFollowRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        return StoreFollowMapper.INSTANCE.toStoreFollowResponseDtoList(followedRepository.findAllByUserLogin(userLoginExtract));
    }

    @Override
    public StoreFollowResponseDTO postStoreFollow(StoreFollowRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        Followed followed = Followed.builder().store(store).userAccount(userAccount).statusFollow(true).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).build();
        followedRepository.save(followed);
        return StoreFollowMapper.INSTANCE.toStoreFollowResponseDto(followed);
    }

    @Override
    public StoreFollowResponseDTO postStoreUnFollow(StoreFollowRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
       Followed followed = followedRepository.findAllByUserLoginAndStore(userLoginExtract,requestDTO.getStoreCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong tim thay!"));
        followedRepository.delete(followed);
        return StoreFollowMapper.INSTANCE.toStoreFollowResponseDto(followed);
    }
}
