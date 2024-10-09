package dto.user_auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserLoginResponseDTO {

    String userLogin;

    String userId;

    String username;

    String userImage;

    String storeStatus;

    String storeName;

    String token;

}
