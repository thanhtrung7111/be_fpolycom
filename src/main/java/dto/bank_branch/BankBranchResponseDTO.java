package dto.bank_branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankBranchResponseDTO {
    String bankBranchCode;

    String name;

    String bankCode;
}
