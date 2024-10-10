package dto.role_admin;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {
    @NotBlank(message = "Khong de trong roleCode")
    String roleCode;
    @NotBlank(message = "Khong de trong ten")
    String name;
    Integer rightCode;

}
