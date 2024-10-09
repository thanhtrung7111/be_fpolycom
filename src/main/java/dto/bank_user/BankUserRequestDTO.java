package dto.bank_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUserRequestDTO {

    String userLogin;

    String passwordBank;

    String accountNumber;

    String accountName;

    Long bankBranchCode;

    Long bankCode;
}
