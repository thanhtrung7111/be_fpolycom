package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfOrdersDayByDayResponseDTO {
    Long numberOfOrders;

    Date date;

}
