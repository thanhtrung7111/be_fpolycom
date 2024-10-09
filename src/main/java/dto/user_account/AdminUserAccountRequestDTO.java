package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserAccountRequestDTO {
    String userAccountID;

    String name;

    String phone;

    String addressDetail;

    String adress;

    String email;

    String dateOfBirth;

    Boolean gender;

    String provinceCode;

    String districtCode;

    String wardCode;


}
