package service.store_group_mess;

import dto.store_group_mess.StoreGroupMessRequestDTO;
import dto.store_group_mess.StoreGroupMessResponseDTO;

import java.util.List;

public interface StoreGroupMessService {

    public StoreGroupMessResponseDTO postNewGroup(StoreGroupMessRequestDTO requestDTO);

    public List<StoreGroupMessResponseDTO> getAllGroupByStore(Long storeCode);

    public List<StoreGroupMessResponseDTO> getAllGroupByUser(Long userCode);
}
