package dto.auth_admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthAdminResponseDTO {

    String username;

    String userImage;

    String token;
}
