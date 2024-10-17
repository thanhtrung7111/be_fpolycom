package dto.adminrole;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleRequestDTO {
    Integer adminRoleCode;
    @NotBlank(message = "Khong de trong roleCode")
String roleCode;
    @NotBlank(message = "Khong de trong adminCode")
    String adminCode;
}
