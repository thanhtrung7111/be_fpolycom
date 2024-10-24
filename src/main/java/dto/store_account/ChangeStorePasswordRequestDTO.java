package dto.store_account;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStorePasswordRequestDTO {
    @NotNull(message = "Khong de trong mat khau hien tai")
    String currentPassword;

    @NotNull(message = "Khong de trong ten dang nhap")
    Long storeCode;

    @NotNull(message = "Hay nhap mat khau ban muon thay doi")
    String newPassword;

    @NotNull(message = "Phai xac nhan mat khau moi")
    String confirmPassword;
}
