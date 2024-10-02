package dto.user_account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountRegisterResponseDTO {

    String name;

    String phone;

    String addressDetail;

    String address;

    String email;

    Date dateOfBirth;

    Boolean gender;

    String provinceName;

    String districtName;

    String wardName;

}
