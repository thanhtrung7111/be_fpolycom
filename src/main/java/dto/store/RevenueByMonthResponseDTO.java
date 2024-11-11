package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueByMonthResponseDTO {
    Integer month;

    Double value;

    String nameStore;

    Long numberOfOrders;

}
