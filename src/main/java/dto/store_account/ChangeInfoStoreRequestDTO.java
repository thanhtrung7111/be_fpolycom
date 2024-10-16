package dto.store_account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeInfoStoreRequestDTO {
    Long storeCode;

    @NotNull(message = "Khong duoc de trong ten")
    @NotBlank (message = "Dien thong tin khong de trong")
    String name;

    String image;

    @NotNull(message = "Khong duoc de trong dia chi")
    @NotBlank (message = "Dien thong tin khong de trong")
    String addressDetail;

    @NotNull(message = "Khong duoc de trong dia chi")
    @NotBlank (message = "Dien thong tin khong de trong")
    String address;

    @NotNull(message = "Khong duoc de trong email")
    @NotBlank (message = "Dien thong tin khong de trong")
    String email;

    @NotNull(message = "Khong duoc de trong so dien thoai")
    @NotBlank (message = "Dien thong tin khong de trong")
    String phone;


    @NotNull(message = "Khong duoc de trong thanh pho")
    String provinceCode;

    @NotNull(message = "Khong duoc de trong quan/huyen")
    String districtCode;

    @NotNull(message = "Khong duoc de trong phuong/xa")
    String wardCode;

    String bannerImage;
}
