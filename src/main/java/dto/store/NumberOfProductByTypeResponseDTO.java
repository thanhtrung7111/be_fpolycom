package dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumberOfProductByTypeResponseDTO {
    Long numberOfProduct;
    String type;
}
