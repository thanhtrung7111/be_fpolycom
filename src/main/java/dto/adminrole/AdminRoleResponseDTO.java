package dto.adminrole;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleResponseDTO {
    Integer adminRoleCode;
    String roleCode;

    String adminCode;
}
