package dto.store_group_mess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreGroupMessRequestDTO {

    Long groupCode;

    Long userCode;

    Long storeCode;

}
