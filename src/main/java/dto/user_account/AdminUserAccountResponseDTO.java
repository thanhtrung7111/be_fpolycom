package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserAccountResponseDTO {
    String userAccountID;

    String name;

    String phone;

    String addressDetail;

    String adress;

    String email;

    String userStatus;

    String dateOfBirth;

    Boolean gender;

    String provinceName;

    String districtName;

    String wardName;


}
