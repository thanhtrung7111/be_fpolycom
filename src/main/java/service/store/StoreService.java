package service.store;

import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;
import dto.store.StoreResponseDTO;
import dto.store.UserStoreDetailResponseDTO;
import entity.enum_package.StoreStatus;

import java.util.List;
import java.util.Optional;

public interface StoreService {


    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO);
    public StoreRegisterResponseDTO updateRegisterStore(StoreRegisterRequestDTO requestDTO);

    public StoreRegisterResponseDTO getRegisterStore(String userLogin,Long storeCode);

    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode);


    public List<StoreResponseDTO> getAllStoreByStatus(StoreStatus storeStatus);

    public List<StoreResponseDTO> getAll();


    public StoreResponseDTO approveStore(Long storeCode);

    public StoreResponseDTO lockStore(Long storeCode);
}
