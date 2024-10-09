package service.auth_user;

import dto.user_auth.AuthUserLoginResponseDTO;

public interface AuthUserService  {


    public AuthUserLoginResponseDTO getUser(String username);
    public String getUserLoginAuthenication();

    public String extractUserlogin(String userLogin);


    public boolean isValidUserLogin (String rawUserLogin);
}
