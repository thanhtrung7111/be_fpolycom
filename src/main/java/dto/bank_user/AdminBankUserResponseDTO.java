package dto.bank_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBankUserResponseDTO {

    String userLogin;

    Long bankUserCode;

    String accountNumber;

    String accountName;

    String bankName;

    Long bankCode;

    Long bankBranchCode;

    String bankShortName;

    String bankBranchName;

    String bankImage;

    String bankStatus;

}
