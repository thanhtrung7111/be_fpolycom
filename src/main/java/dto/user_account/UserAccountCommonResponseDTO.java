package dto.user_account;

import entity.enum_package.FriendshipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountCommonResponseDTO {

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


    Long districtCode;


    Long wardCode;

    String provinceName;

    String districtName;

    String wardName;

    Date createdDate;

    FriendshipStatus friendshipStatus;
}
