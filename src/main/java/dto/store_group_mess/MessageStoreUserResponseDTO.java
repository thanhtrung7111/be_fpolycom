package dto.store_group_mess;

import entity.enum_package.TypeMessage;
import entity.enum_package.TypeSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageStoreUserResponseDTO {
    String message;

    TypeMessage typeMessage;

    LocalDateTime timeSend;

    Long idSender;

    TypeSender typeSender;

    Long messageCode;

    Long groupMessageCode;

    String storeName;

    String username;

    String storeImage;

    String userImage;

    Boolean readed;

    Long storeCode;

    Long userCode;
}
