package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRequestDTO {

    String userLogin;

    String password;


    String name;

    String phone;

    String addressDetail;

    String address;

    String email;

    Date dateOfBirth;

    Boolean gender;

    String provinceCode;

    String districtCode;

    String wardCode;



}
