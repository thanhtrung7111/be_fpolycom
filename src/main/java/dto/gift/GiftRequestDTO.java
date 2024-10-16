package dto.gift;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftRequestDTO {

    String userLogin;

    Long userCode;

    Long orderCode;

    String content;
}
