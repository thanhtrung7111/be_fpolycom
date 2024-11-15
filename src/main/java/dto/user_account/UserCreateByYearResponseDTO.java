package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateByYearResponseDTO {
    Long numberOfUserCreate;

    Integer year;



}
