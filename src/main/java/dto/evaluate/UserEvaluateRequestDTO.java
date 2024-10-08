package dto.evaluate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvaluateRequestDTO {

   String title;

   String content;

   Integer quality;

   List<EvaluateImageRequestDTO> imageList;

   String userLogin;

   Long productCode;

}
