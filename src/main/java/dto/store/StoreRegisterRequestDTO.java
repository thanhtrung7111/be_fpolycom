package dto.store;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequestDTO {

    String userLogin;

    String storeRegisterCode;

    String image;

    String name;

    String addressDetail;

    String address;

    String phone;

    String bannerImage;

    String email;

    String password;

    Long provinceCode;

    Long districtCode;

    Long wardCode;


    List<StoreDocumentRequestDTO> documentList;


}
