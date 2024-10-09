package dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductApproveResponeDTO {
Long productCode;
String name;
String productStatusCode;
}
