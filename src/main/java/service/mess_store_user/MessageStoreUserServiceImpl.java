package service.mess_store_user;

import dao.GroupStoreMessageRepository;
import dao.MessageStoreUserRepository;
import dto.store_group_mess.MessageStoreUserMapper;
import dto.store_group_mess.MessageStoreUserRequestDTO;
import dto.store_group_mess.MessageStoreUserResponseDTO;
import entity.GroupMessageStore;
import entity.MessageStoreUser;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.common.SendMessageService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessageStoreUserServiceImpl implements MessageStoreUserService {

    @Autowired
    MessageStoreUserRepository messageStoreUserRepository;


    @Autowired
    GroupStoreMessageRepository groupStoreMessageRepository;

    @Override
    public List<MessageStoreUserResponseDTO> postNew(List<MessageStoreUserRequestDTO> requestDTO) {
        GroupMessageStore groupMessageStore = groupStoreMessageRepository.findById(requestDTO.get(0).getGroupMessageCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        List<MessageStoreUser> messageStoreUser = MessageStoreUserMapper.INSTANCE.toMessageStoreUserList(requestDTO);
        AtomicInteger index = new AtomicInteger(0);
        messageStoreUser.forEach(item->{
           item.setGroupMessageStore(groupMessageStore);
           item.setCreatedDate(new Date());
           item.setUpdatedDate(null);
           item.setDeleted(false);
           item.setReaded(false);
           item.setTimeSend(LocalDateTime.now().plusSeconds(index.getAndIncrement()));
           item.setDeletedDate(null);
       });
       List<MessageStoreUser> saved=  messageStoreUserRepository.saveAll(messageStoreUser);
//    System.out.println(saved);
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDtoList(saved);
    }

    @Override
    public List<MessageStoreUserResponseDTO> getAllLatestMessageByStore(Long storeCode) {
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDtoList(messageStoreUserRepository.findAllMessageLatestByStore(storeCode));
    }

    @Override
    public List<MessageStoreUserResponseDTO> getAllLatestMessageByUser(Long userCode) {
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDtoList(messageStoreUserRepository.findAllMessageLatestByUser(userCode));
    }

    @Override
    public List<MessageStoreUserResponseDTO> getAllByGroup(Long groupCode) {
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDtoList(messageStoreUserRepository.findAllByGroundMess(groupCode));
    }

    @Override
    public MessageStoreUserResponseDTO readMessage(Long messageCode) {
        MessageStoreUser messageStoreUser = messageStoreUserRepository.findById(messageCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        messageStoreUser.setReaded(true);
        messageStoreUserRepository.save(messageStoreUser);
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDto(messageStoreUser);
    }


}
