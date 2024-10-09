package dto.bank_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserResponseDTO {

    Long bankUserCode;

    String accountNumber;

    String accountName;

    String bankName;

    String bankBranchName;

    String bankImage;

    String bankStatus;

}
