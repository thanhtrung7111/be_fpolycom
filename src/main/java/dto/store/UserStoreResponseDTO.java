package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStoreResponseDTO {
    String storeRegisterCode;

    String image;

    String name;

    String addressDetail;

    String address;

    String phone;

    String status;

    String bannerImage;

    String email;

    String userRegister;

   String provinceName;

    String districtName;

    String wardName;

    String  numberOfFollowed;

}
