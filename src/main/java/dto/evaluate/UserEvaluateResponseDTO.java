package dto.evaluate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvaluateResponseDTO {


    String title;

    String content;

    Integer quality;

    List<EvaluateImageRequestDTO> imageList;

    String userCode;

    Date dateEvaluate;

    Long productCode;
}
