package service.mess_store_user;

import dao.MessageStoreUserRepository;
import dto.store_group_mess.MessageStoreUserMapper;
import dto.store_group_mess.MessageStoreUserRequestDTO;
import dto.store_group_mess.MessageStoreUserResponseDTO;
import entity.MessageStoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.common.SendMessageService;

import java.util.Date;
import java.util.List;

@Service
public class MessageStoreUserServiceImpl implements MessageStoreUserService {

    @Autowired
    MessageStoreUserRepository messageStoreUserRepository;

    @Autowired
    SendMessageService sendMessageService;

    @Override
    public MessageStoreUserResponseDTO postNew(MessageStoreUserRequestDTO requestDTO) {
        MessageStoreUser messageStoreUser = MessageStoreUserMapper.INSTANCE.toMessageStoreUser(requestDTO);
        messageStoreUser.setCreatedDate(new Date());
        messageStoreUser.setUpdatedDate(null);
        messageStoreUser.setDeleted(false);
        messageStoreUser.setDeletedDate(null);
        messageStoreUserRepository.save(messageStoreUser);
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDto(messageStoreUser);
    }

    @Override
    public List<MessageStoreUserResponseDTO> getAllMessageByGroup(Long groupCode) {
        return MessageStoreUserMapper.INSTANCE.toMessageStoreUserResponseDtoList(messageStoreUserRepository.findAllByGroundMess(groupCode));
    }
}
