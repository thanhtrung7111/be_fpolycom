package service.store;

import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;
import dto.store.StoreResponseDTO;
import dto.store.UserStoreDetailResponseDTO;
import dto.store_account.ChangeInfoStoreRequestDTO;
import dto.store_account.ChangeInfoStoreResponseDTO;
import dto.store_account.ChangeStorePasswordRequestDTO;
import dto.store_account.ChangeStorePasswordResponseDTO;
import entity.Store;
import entity.enum_package.StoreStatus;
import java.util.List;
import java.util.Optional;

public interface StoreService {


    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO);
    public StoreRegisterResponseDTO updateRegisterStore(StoreRegisterRequestDTO requestDTO);

    public StoreRegisterResponseDTO getRegisterStore(String userLogin,Long storeCode);

    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode,String userLogin);


    public ChangeStorePasswordResponseDTO changeStorePassword(ChangeStorePasswordRequestDTO requestDTO);

    public ChangeInfoStoreResponseDTO changeInfoStore(ChangeInfoStoreRequestDTO requestDTO);
    public List<StoreResponseDTO> getAllStoreByStatus(StoreStatus storeStatus);

    public List<StoreResponseDTO> getAll();


    public StoreResponseDTO approveStore(Long storeCode);

    public StoreResponseDTO lockStore(Long storeCode);

}
