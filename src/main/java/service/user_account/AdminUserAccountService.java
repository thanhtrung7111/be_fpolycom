package service.user_account;

import dto.user_account.*;

import java.util.List;

public interface AdminUserAccountService<DTORequest,DTOResponse> {
    public List<DTORequest> getAll();


    AdminUserAccountResponseDTO lockUser(AdminUserAccountRequestDTO request);

    AdminUserAccountResponseDTO unlockUser(AdminUserAccountRequestDTO request);
}
