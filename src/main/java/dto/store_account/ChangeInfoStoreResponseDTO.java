package dto.store_account;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeInfoStoreResponseDTO {
    Long storeCode;

    String name;

    String image;

    String addressDetail;

    String address;

    String email;

    String phone;

    String provinceCode;

    String districtCode;

    String wardCode;

    String bannerImage;
}
