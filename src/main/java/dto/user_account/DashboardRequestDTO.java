package dto.user_account;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardRequestDTO {
    Integer year;

    Integer month;

    Date startDate;

    Date endDate;

    Integer startYear;

    Integer endYear;

    Date date;



}
