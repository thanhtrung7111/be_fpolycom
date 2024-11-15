package service.mess_store_user;


import dto.store_group_mess.MessageStoreUserRequestDTO;
import dto.store_group_mess.MessageStoreUserResponseDTO;

import java.util.List;

public interface MessageStoreUserService {


    public List<MessageStoreUserResponseDTO> postNew(List<MessageStoreUserRequestDTO> requestDTO);

    public List<MessageStoreUserResponseDTO> getAllLatestMessageByStore(Long storeCode);

    public List<MessageStoreUserResponseDTO> getAllLatestMessageByUser(Long userCode
    );

    public List<MessageStoreUserResponseDTO> getAllByGroup(Long groupCode
    );

    public MessageStoreUserResponseDTO readMessage(Long messageCode
    );
}
