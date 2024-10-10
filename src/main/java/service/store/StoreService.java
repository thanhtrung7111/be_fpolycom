package service.store;

import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;

public interface StoreService {


    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO);


}
