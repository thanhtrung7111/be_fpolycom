package dto.type_good_attr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeGoodAttrRequestDTO {
    String name;

    String typeGoodAttrCode;

    String typeGoodCode;
}
