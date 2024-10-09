package user_controller;

import dto.store_follow.StoreFollowRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store_follow.StoreFollowService;

@RestController
@RequestMapping(value = "/user")
public class UserRelationStoreController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreFollowService storeFollowService;

    @PostMapping(value = "/store/all-follow")
    public ResponseEntity<Object> getAllStoreFollow(@RequestBody StoreFollowRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(storeFollowService.getAllStoreFollowed(requestDTO)));
    }

    @PostMapping(value = "/store/follow")
    public ResponseEntity<Object> postStoreFollow(@Valid @RequestBody StoreFollowRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(storeFollowService.postStoreFollow(requestDTO)));
    }

    @PostMapping(value = "/store/unfollow")
    public ResponseEntity<Object> postStoreUnFollow(@Valid @RequestBody StoreFollowRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(storeFollowService.postStoreUnFollow(requestDTO)));
    }



}
