package dto.user_account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRegisterRequestDTO {

    @NotBlank(message = "Khong de trong ten dang nhap!")
    String userLogin;

    @NotBlank(message = "Khong de trong mat khau!")
    String password;

    @NotBlank(message = "Khong de trong ten!")
    String name;

    @NotBlank(message = "Khong de trong dien thoai")
    String phone;

    @NotBlank(message = "Khong de trong dia chi cu the")
    String addressDetail;

    @NotBlank(message = "khong de trong dia chi!")
    String address;

    @NotBlank(message = "Khong de trong email!")
    String email;

    @NotNull(message = "KHong de trong ngay sinh!")
    Date dateOfBirth;

    @NotNull(message = "Khong de trong gioi tinh!")
    Boolean gender;

    @NotBlank(message = "Khong de trong ma tinh!")
    String provinceCode;

    @NotBlank(message = "Khong de trong ma quan!")
    String districtCode;

    @NotBlank(message = "Khong de trong ma thi xa")
    String wardCode;



}
