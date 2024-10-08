package dto.store_follow;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreFollowRequestDTO {

    @NotBlank(message = "Khong de trong userLogin!")
    String userLogin;

    @NotNull(message = "Khong de trong id cua hang!")
    Long storeCode;


}
