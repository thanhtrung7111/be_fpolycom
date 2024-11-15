package dto.user_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueByStoreInMonthResponseDTO {

    Integer month;

    Integer year;

    Double revenue;

    String storeName;

    Long numberOfOrders;
}
