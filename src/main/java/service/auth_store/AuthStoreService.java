package service.auth_store;

import dto.auth_store.AuthStoreLoginResponseDTO;

public interface AuthStoreService {

    public AuthStoreLoginResponseDTO getStoreByUser (String userLogin);

    public boolean isValidStore(Long storeID);
}
