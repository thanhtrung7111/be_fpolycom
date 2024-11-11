package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueYearsResponseDTO {
    Integer year;

    Double value;

    String nameStore;

    Long numberOfOrders;
}
