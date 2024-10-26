package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStoreDetailResponseDTO {

    Long storeCode;
    String image;

    String name;

    String addressDetail;

    String address;

    String phone;

    String status;

    String bannerImage;

    Boolean followed;

    String email;

    String userRegister;

    String provinceName;

    String districtName;

    String wardName;

    String  numberOfFollowed;

    String  numberOfLiked;
}
