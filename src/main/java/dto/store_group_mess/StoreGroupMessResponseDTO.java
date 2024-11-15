package dto.store_group_mess;

import entity.enum_package.TypeMessage;
import entity.enum_package.TypeSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreGroupMessResponseDTO {

    Long storeCode;

    Long userCode;

    String username;

    String storeName;

    String storeImage;

    String userImage;

    Long groupCode;

//    List<MessageStoreUserResponseDTO> messageStoreUserList;

}
