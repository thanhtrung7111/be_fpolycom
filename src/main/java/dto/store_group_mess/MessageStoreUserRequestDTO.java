package dto.store_group_mess;

import entity.enum_package.TypeMessage;
import entity.enum_package.TypeSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageStoreUserRequestDTO {
    String message;

    TypeMessage typeMessage;

    Long idSender;

    TypeSender typeSender;

    Long groupMessageCode;

    Long messageCode;

    Long storeCode;

    Long userCode;


}
