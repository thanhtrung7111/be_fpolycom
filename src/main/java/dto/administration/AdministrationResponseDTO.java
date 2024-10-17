package dto.administration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministrationResponseDTO {
    String adminCode;
    String userLogin;
    String password;
    String name;
    String image;
    String email;
    String phone;
}
