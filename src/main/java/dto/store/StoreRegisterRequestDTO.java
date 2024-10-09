package dto.store;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequestDTO {

    String image;

    String name;

    String addressDetail;

    String address;

    String phone;

    String email;

    String password;

    String userLogin;

    Long provinceCode;

    Long districtCode;

    Long wardCode;



}
