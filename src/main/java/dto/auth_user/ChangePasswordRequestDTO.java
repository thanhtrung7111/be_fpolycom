package dto.auth_user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {

    @NotBlank(message = "Khong de trong userLogin!")
    String userLogin;

    @NotBlank(message = "Khong de trong mat khau hien tai")
    String passwordCurrent;

    @NotBlank(message = "Khong de trong mat khau moi")
    String passwordNew;

}
