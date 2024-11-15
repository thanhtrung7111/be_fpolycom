package store_controller;

import dto.store_group_mess.MessageStoreUserRequestDTO;
import dto.store_group_mess.MessageStoreUserResponseDTO;
import dto.store_group_mess.StoreGroupMessRequestDTO;
import dto.store_group_mess.StoreGroupMessResponseDTO;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.common.SendMessageService;
import service.data_return.DataReturnService;
import service.mess_store_user.MessageStoreUserService;
import service.store_group_mess.StoreGroupMessService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreMessageUserController {

    @Autowired
    DataReturnService dataReturnService;


    @Autowired
    StoreGroupMessService storeGroupMessService;

    @Autowired
    MessageStoreUserService messageStoreUserService;

    @Autowired
    SendMessageService sendMessageService;

    @PostMapping("/group-message/all")
    public ResponseEntity<Object> getAllMessageLatest(@RequestBody HashMap<String, Long> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        return ResponseEntity.ok(dataReturnService.success(messageStoreUserService.getAllLatestMessageByStore(request.get("storeCode"))));
    }

    @PostMapping("/group/all")
    public ResponseEntity<Object> getAllGroup(@RequestBody HashMap<String, Long> request) {
        if (request.isEmpty() || request.get("storeCode") == null) {
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        return ResponseEntity.ok(dataReturnService.success(storeGroupMessService.getAllGroupByStore(request.get("storeCode"))));
    }

    @PostMapping("/message/send")
    public ResponseEntity<Object> sendMessage(@RequestBody List<MessageStoreUserRequestDTO> requestDTO) {
        List<MessageStoreUserResponseDTO> responseDTO = messageStoreUserService.postNew(requestDTO);
        sendMessageService.sendMessageFromStore(requestDTO.getLast().getUserCode(), responseDTO);
        return ResponseEntity.ok(dataReturnService.success(responseDTO));
    }

    @PostMapping("/message/create-group")
    public ResponseEntity<Object> createNewGroup(@RequestBody StoreGroupMessRequestDTO requestDTO) {
        StoreGroupMessResponseDTO responseDTO = storeGroupMessService.postNewGroup(requestDTO);
        return ResponseEntity.ok(dataReturnService.success(responseDTO));
    }

    @PostMapping("/message/read")
    public ResponseEntity<Object> createNewGroup(@RequestBody HashMap<String,Long> requestDTO)  {
        if(requestDTO.isEmpty() || requestDTO.get("messageCode") == null){
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        MessageStoreUserResponseDTO responseDTO = messageStoreUserService.readMessage(requestDTO.get("messageCode"));
        return ResponseEntity.ok(dataReturnService.success(responseDTO));
    }

    @PostMapping("/message/all")
    public ResponseEntity<Object> getAllMessage(@RequestBody HashMap<String,Long> requestDTO)  {
        if(requestDTO.isEmpty() || requestDTO.get("groupCode") == null){
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        List<MessageStoreUserResponseDTO> responseDTO = messageStoreUserService.getAllByGroup(requestDTO.get("groupCode"));
        return ResponseEntity.ok(dataReturnService.success(responseDTO));
    }

}
