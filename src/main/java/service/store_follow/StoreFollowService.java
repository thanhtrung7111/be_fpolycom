package service.store_follow;

import dto.store_follow.StoreFollowRequestDTO;
import dto.store_follow.StoreFollowResponseDTO;

import java.util.List;

public interface StoreFollowService {


    public List<StoreFollowResponseDTO> getAllStoreFollowed(StoreFollowRequestDTO requestDTO);


    public StoreFollowResponseDTO postStoreFollow(StoreFollowRequestDTO requestDTO);
    public StoreFollowResponseDTO postStoreUnFollow(StoreFollowRequestDTO requestDTO);
}
