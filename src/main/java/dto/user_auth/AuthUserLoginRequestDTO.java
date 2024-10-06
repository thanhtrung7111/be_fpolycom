package dto.user_auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserLoginRequestDTO {
    @NotBlank(message = "Sai thông thin dăng nhập")
    private String username;

    @NotBlank(message = "Sai thng tin ật khâẩu")
    private String password;
}
