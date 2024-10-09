package dto.user_account;

import entity.District;
import entity.Province;
import entity.Ward;
import entity.enum_package.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountChangeRequestDTO {

    String userLogin;

    String name;

    String phone;

    String addressDetail;


    String address;

    String image;

    String bannerImage;


    Date dateOfBirth;

    Boolean gender;


    Long provinceCode;


    Long districtCode;

    Long wardCode;

}
