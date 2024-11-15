package dto.store_group_mess;

import entity.enum_package.TypeMessage;
import entity.enum_package.TypeSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    String message;

    TypeMessage typeMessage;

    TypeSender typeSender;


}
