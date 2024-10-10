package service.auth_admin;

import dto.auth_admin.AuthAdminResponseDTO;
import dto.auth_store.AuthStoreLoginResponseDTO;

public interface AuthAdminService {

    public AuthAdminResponseDTO getAdmin (String userLogin);
    public String extractUserlogin(String userLogin);
    public boolean isValidUserLogin(String rawUserLogin);
}
