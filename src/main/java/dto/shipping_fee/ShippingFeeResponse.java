package dto.shipping_fee;

import entity.enum_package.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingFeeResponse {

    Double fee;

    TypeShipping typeShipping;

    Long shippingFeeCode;
}
