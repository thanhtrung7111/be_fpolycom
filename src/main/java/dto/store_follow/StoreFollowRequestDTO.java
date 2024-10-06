package dto.store_follow;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreFollowRequestDTO {

    @NotBlank(message = "Khong de trong userLogin!")
    String userLogin;

    @NotBlank(message = "Khong de trong id cua hang!")
    Long storeCode;


}
