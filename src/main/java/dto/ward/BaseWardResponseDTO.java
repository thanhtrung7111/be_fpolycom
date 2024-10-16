package dto.ward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseWardResponseDTO {

    Long wardCode;

    String name;
}
