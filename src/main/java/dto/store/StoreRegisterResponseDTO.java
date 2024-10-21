package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterResponseDTO {

    String storeRegisterCode;

    String image;

    String name;

    String addressDetail;

    String address;

    String status;

    String phone;

    String bannerImage;

    String email;

    String userRegister;

    Long provinceCode;

    Long districtCode;

    Long wardCode;


    List<StoreDocumentResponseDTO> documentList;
}
