package user_controller;

import dto.liked_product.LikedProductRequestDTO;
import dto.store_follow.StoreFollowRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.liked_product.LikedProductService;

@RestController
@RequestMapping(value = "/user")
public class UserProductLikedController {


    @Autowired
    LikedProductService service;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping(value = "/product/liked-all")
    public ResponseEntity<Object> getAllLikedAll(@RequestBody LikedProductRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(service.getAllLikedProduct(requestDTO)));
    }

    @PostMapping(value = "/product/liked")
    public ResponseEntity<Object> postProductLiked(@RequestBody LikedProductRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(service.postLikedProduct(requestDTO)));
    }

    @PostMapping(value = "/product/unliked")
    public ResponseEntity<Object> postProductUnLiked(@RequestBody LikedProductRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(service.postUnlikedProduct(requestDTO)));
    }

}
