package service.common;

import dto.store_group_mess.MessageStoreUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendMessageService {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Async("taskExecutor")
    public void sendMessageFromStore(Long userCode, List<MessageStoreUserResponseDTO> responseDTO){
        messagingTemplate.convertAndSend("/topic/message/user/"+userCode,responseDTO);
        System.out.println("Send success");
    }


    @Async("taskExecutor")
    public void sendMessageFromUser(Long storeCode,List<MessageStoreUserResponseDTO> responseDTO){
        messagingTemplate.convertAndSend("/topic/message/store/"+storeCode,responseDTO);
        System.out.println("Send success");
    }

}
