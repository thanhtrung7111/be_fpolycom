package dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductApproveRequestDTO {
    @NotBlank(message = "Truong du lieu ProductCode trong")
    Long productCode;
    @NotBlank(message = "Truong du lieu name trong")
    String name;
    @NotBlank(message = "Truong du lieu ProductStatusCode trong")
    String productStatusCode;


}
