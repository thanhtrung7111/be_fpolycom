package dto.type_good;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeGoodRequestDTO {
    String typeGoodCode;

    String name;
}
