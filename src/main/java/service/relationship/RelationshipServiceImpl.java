package service.relationship;

import dao.RelationshipRepository;
import dao.UserAccountRepository;
import dto.relationship.RelationshipMapper;
import dto.relationship.RelationshipRequestDTO;
import dto.relationship.RelationshipResponseDTO;
import entity.Relationship;
import entity.UserAccount;
import entity.enum_package.FriendshipStatus;
import entity.enum_package.TypeNotifycationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;
import service.user_notify.UserNotifyService;

import java.util.Date;
import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    RelationshipRepository relationshipRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserNotifyService userNotifyService;

    @Override
    public RelationshipResponseDTO postData(RelationshipRequestDTO relationshipRequestDTO) {
        return null;
    }

    @Override
    public RelationshipResponseDTO updateData(RelationshipRequestDTO relationshipRequestDTO) {
        return null;
    }

    @Override
    public RelationshipResponseDTO deleteData(RelationshipRequestDTO relationshipRequestDTO) {
        return null;
    }


    @Override
    public List<RelationshipResponseDTO> getAllData() {
        return null;
    }

    @Override
    public RelationshipResponseDTO getDetailData(Long aLong) {
        return null;
    }

    public List<RelationshipResponseDTO> getAllRelationByUser(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        return RelationshipMapper.INSTANCE.toRelationshipResponseDtoList(relationshipRepository.getAllRelationshipByUserAndStatus(userLoginExtract, FriendshipStatus.accepted));
    }

    @Override
    public List<RelationshipResponseDTO> getAllRelationByUserPending(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        return RelationshipMapper.INSTANCE.toRelationshipResponseDtoList(relationshipRepository.getAllRelationshipByUserAndStatus(userLoginExtract, FriendshipStatus.pending));
    }

    @Override
    public RelationshipResponseDTO postFriendRequest(RelationshipRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccountPrimary = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        UserAccount userAccountSecondary = userAccountRepository.findById(requestDTO.getUserCodeSecond()).orElseThrow(() -> new UsernameNotFoundException("Nguoi ket ban khong ton tai!"));
        List<Relationship> relationships = relationshipRepository.getAllRelationShipByUserAndUser(userAccountPrimary, userAccountSecondary);

        if(relationships.size()>0){
            throw new RuntimeException("Bạn da ket ban voi nguoi nay!");
        }
        Relationship relationship = Relationship.builder().userAccountPrimary(userAccountPrimary).userAccountSecondary(userAccountSecondary).friendshipStatus(FriendshipStatus.friendRequest).createdDate(new Date()).deleted(false).updatedDate(null).deletedDate(null).build();


        Relationship relationship2 = Relationship.builder().userAccountPrimary(userAccountSecondary).userAccountSecondary(userAccountPrimary).friendshipStatus(FriendshipStatus.pending).createdDate(new Date()).deleted(false).updatedDate(null).deletedDate(null).build();

        relationshipRepository.saveAll(List.of(relationship, relationship2));

        Relationship relationshipNotify = Relationship.builder().userAccountPrimary(userAccountPrimary).userAccountSecondary(userAccountPrimary).friendshipStatus(FriendshipStatus.friendRequest).createdDate(new Date()).deleted(false).updatedDate(null).deletedDate(null).build();
        userNotifyService.sendAddFriendToUser(userAccountSecondary.getId(),relationshipNotify);

        return RelationshipMapper.INSTANCE.toRelationshipResponseDto(relationship);
    }

    @Override
    public RelationshipResponseDTO postFriendAccept(RelationshipRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccountPrimary = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        UserAccount userAccountSecondary = userAccountRepository.findById(requestDTO.getUserCodeSecond()).orElseThrow(() -> new UsernameNotFoundException("Nguoi ket ban khong ton tai!"));
        List<Relationship> relationships = relationshipRepository.getAllRelationShipByUserAndUser(userAccountPrimary, userAccountSecondary);
        relationships.forEach(item -> item.setFriendshipStatus(FriendshipStatus.accepted));
        relationshipRepository.saveAll(relationships);

        userNotifyService.sendNotifyToUser("Yêu cầu kết bạn",userAccountPrimary.getName()+" chấp nhận lời yêu cầu kết bạn!",userAccountPrimary.getId().toString(), TypeNotifycationUser.friend,userAccountPrimary.getImage(), userAccountSecondary.getId());

        return RelationshipMapper.INSTANCE.toRelationshipResponseDto(relationships.stream().filter(item->item.getUserAccountSecondary().getId() == requestDTO.getUserCodeSecond()).findFirst().get());
    }

    @Override
    public RelationshipResponseDTO postFriendCancel(RelationshipRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong so huu danh sach nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccountPrimary = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        UserAccount userAccountSecondary = userAccountRepository.findById(requestDTO.getUserCodeSecond()).orElseThrow(() -> new UsernameNotFoundException("Nguoi ket ban khong ton tai!"));
        List<Relationship> relationships = relationshipRepository.getAllRelationShipByUserAndUser(userAccountPrimary, userAccountSecondary);
        relationshipRepository.deleteAll(relationships);
        return RelationshipMapper.INSTANCE.toRelationshipResponseDto(relationships.stream().filter(item->item.getUserAccountSecondary().getId() == requestDTO.getUserCodeSecond()).findFirst().get());
    }
}
