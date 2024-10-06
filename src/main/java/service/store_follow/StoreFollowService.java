package service.store_follow;

import dto.store_follow.StoreFollowRequestDTO;
import dto.store_follow.StoreFollowResponseDTO;

import java.util.List;

public interface StoreFollowService {


    public List<StoreFollowResponseDTO> getAllStoreFollowed(StoreFollowRequestDTO requestDTO);
}
