package dto.accept_store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptStoreRequestDTO {
    String storeID;

    String name;

    String image;

    String addressDetail;

    String address;

    String phone;

    String email;

    String password;

    String bannerImage;

    String provinceCode;

    String districtCode;

    String wardCode;


}
