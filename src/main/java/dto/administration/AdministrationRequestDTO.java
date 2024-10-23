package dto.administration;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministrationRequestDTO {
    @NotBlank(message = "Ma khong de trong")
    String adminCode;
    @NotBlank(message = "Userlogin khong de trong")
    String userLogin;
    @NotBlank(message = "Password khong de trong")
    String password;
    @NotBlank(message = "Ten khong de trong")
    String name;
    String image;
    @NotBlank(message = "Email khong de trong")
    String email;
    @NotBlank(message = "Ten khong de trong")
    String phone;
}
