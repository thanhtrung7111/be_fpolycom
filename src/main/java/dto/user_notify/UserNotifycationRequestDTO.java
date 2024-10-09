package dto.user_notify;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotifycationRequestDTO {

    @NotBlank(message = "Khong de trong userLogn!")
    String userLogin;

    @NotBlank(message = "Khong de trong userNotifyCode")
    String userNotifyCode;
}
