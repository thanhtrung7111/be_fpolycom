package dto.auth_store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthStoreLoginRequestDTO {

    String userLogin;

    String password;
}
