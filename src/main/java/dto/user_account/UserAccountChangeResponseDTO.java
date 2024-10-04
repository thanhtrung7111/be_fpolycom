package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountChangeResponseDTO {

    String name;

    String phone;

    String addressDetail;

    String address;

    String image;

    String bannerImage;

    String email;

    Date dateOfBirth;

    Boolean gender;

    Long provinceCode;

    String provinceName;

    Long districtCode;

    String districtName;

    Long wardCode;

    String wardName;
}
