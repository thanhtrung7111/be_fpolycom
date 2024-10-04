package dto.auth_user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequestDTO {

    @NotBlank(message = "Khong de trong token recover!")
    @NotNull(message = "Khong de trong truong du lieu token recover!")
    String tokenRecover;

    @NotBlank(message = "Khong de trong mat khau moi!")
    @NotNull(message = "Khong de trong truong du lieu password moi!")
    String passwordNew;

}
