package service.mess_store_user;


import dto.store_group_mess.MessageStoreUserRequestDTO;
import dto.store_group_mess.MessageStoreUserResponseDTO;

import java.util.List;

public interface MessageStoreUserService {


    public MessageStoreUserResponseDTO postNew(MessageStoreUserRequestDTO requestDTO);

    public List<MessageStoreUserResponseDTO> getAllMessageByGroup(Long groupCode);

}
