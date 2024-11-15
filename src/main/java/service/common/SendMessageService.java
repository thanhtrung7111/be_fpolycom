package service.common;

import dto.store_group_mess.MessageStoreUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Async("taskExecutor")
    public void sendMessageFromStore(Long userCode,MessageStoreUserResponseDTO responseDTO){
        messagingTemplate.convertAndSend("/user/"+userCode,responseDTO);
    }


    @Async("taskExecutor")
    public void sendMessageFromUser(Long storeCode,MessageStoreUserResponseDTO responseDTO){
        messagingTemplate.convertAndSend("/store/"+storeCode,responseDTO);
    }

}
