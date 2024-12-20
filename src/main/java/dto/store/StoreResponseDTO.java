package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponseDTO {
    String storeCode;

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
    String  numberOfLiked;

    String rejectReason;
}
