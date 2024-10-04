package dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponseDTO {
    String name;

    String shortName;

    String bankCode;

    String description;

    String image;

    Integer numberOfBankBranch;
}
