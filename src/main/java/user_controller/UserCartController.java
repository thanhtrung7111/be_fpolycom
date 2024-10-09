package user_controller;

import dto.liked_product.LikedProductRequestDTO;
import dto.user_cart.UserCartRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.user_cart.UserCartService;

@RestController
@RequestMapping(value = "/user")
public class UserCartController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    UserCartService userCartService;

    @PostMapping(value = "/cart/all")
    public ResponseEntity<Object> getAllCartByUser(@RequestBody UserCartRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(userCartService.getAllCartByUser(requestDTO)));
    }


    @PostMapping(value = "/cart/new")
    public ResponseEntity<Object> postCartByUser(@Valid @RequestBody UserCartRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(userCartService.postCartByUser(requestDTO)));
    }

    @PostMapping(value = "/cart/update")
    public ResponseEntity<Object> updateCartByUser(@Valid @RequestBody UserCartRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(userCartService.updateCartByUser(requestDTO)));
    }

    @PostMapping(value = "/cart/delete")
    public ResponseEntity<Object> deleteCartByUser(@RequestBody UserCartRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(userCartService.deleteCartByUser(requestDTO)));
    }


}
