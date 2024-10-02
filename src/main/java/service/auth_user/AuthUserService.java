package service.auth_user;

import dto.auth_user.AuthUserLoginResponseDTO;

public interface AuthUserService  {


    public AuthUserLoginResponseDTO getUser(String username);
}
