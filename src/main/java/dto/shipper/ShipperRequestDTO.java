package dto.shipper;

import entity.District;
import entity.Province;
import entity.ReceiveDelivery;
import entity.Ward;
import entity.enum_package.ShipperStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperRequestDTO {
    @NotBlank(message = "Khong de trong shipperCode")
    String shipperCode;

    @NotBlank(message = "Khong de trong userLogin")
    String userLogin;

    @NotBlank(message = "Khong de trong password")
    String password;

    @NotBlank(message = "Khong de trong name")
    String name;

    @NotBlank(message = "Khong de trong address")
    String address;

    @NotBlank(message = "Khong de trong addressDetail")
    String addressDetail;

    @NotBlank(message = "Khong de trong email")
    String email;

    @NotBlank(message = "Khong de trong phone")
    String phone;

    @NotBlank(message = "Khong de trong provinceCode")
    String provinceCode;

    @NotBlank(message = "Khong de trong districtCode")
    String districtCode;

    @NotBlank(message = "Khong de trong wardCode")
    String wardCode;

    String shipperStatus;

}
