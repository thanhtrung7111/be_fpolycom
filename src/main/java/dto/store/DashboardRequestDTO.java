package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardRequestDTO {
    Long storeCode;

    Integer month;

    Integer year;

    Date date;

    Long typeGoodCode;
}
