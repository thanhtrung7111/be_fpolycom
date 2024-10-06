package dto.relationship;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipRequestDTO {

    @NotBlank(message = "Khong de trong ten dang nhap!")
    String userLogin;

    @NotNull(message = "Khong de trong userCode ket ban")
    Long userCodeSecond;
}
