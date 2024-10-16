package service.store;

import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;
import dto.store.UserStoreDetailResponseDTO;
import dto.store_account.ChangeInfoStoreRequestDTO;
import dto.store_account.ChangeInfoStoreResponseDTO;
import dto.store_account.ChangeStorePasswordRequestDTO;
import dto.store_account.ChangeStorePasswordResponseDTO;
import entity.Store;

public interface StoreService {


    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO);


    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode);

    public ChangeStorePasswordResponseDTO changeStorePassword(ChangeStorePasswordRequestDTO requestDTO);

    public ChangeInfoStoreResponseDTO changeInfoStore(ChangeInfoStoreRequestDTO requestDTO);
}
