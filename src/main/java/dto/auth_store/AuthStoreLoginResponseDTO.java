package dto.auth_store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthStoreLoginResponseDTO {

    String storeName;

    String storeImage;

    String storeCode;

    String token;

    String status;

}
