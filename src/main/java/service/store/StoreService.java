package service.store;

import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;
import dto.store.UserStoreDetailResponseDTO;
import entity.Store;

public interface StoreService {


    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO);


    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode);


}
