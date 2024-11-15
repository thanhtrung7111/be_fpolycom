package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDayResponseDTO {
    Date date;

    Double value;

    String nameStore;

    Long numberOfOrders;
}
