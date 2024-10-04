package dto.bank;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRequestDTO {
    @NotBlank(message = "Ten ngan hang khong de trong")
    String name;

    @NotBlank(message = "Ten ngan hang khong de trong")
    String shortName;

    String bankCode;

    String description;

    String image;

}
