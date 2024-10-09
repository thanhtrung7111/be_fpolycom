package dto.type_good;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeGoodResponseDTO {
    String name;

    String typeGoodCode;

    Integer NumberOfProduct;

    Integer NumberOfAttr;
}
