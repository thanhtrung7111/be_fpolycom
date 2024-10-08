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

    List<EvaluateImageResponseDTO> imageList;

    String userCode;

    String username;

    String userImage;

    Date dateEvaluate;

    Long productCode;
}
