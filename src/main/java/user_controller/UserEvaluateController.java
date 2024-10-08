package user_controller;

import dto.user_cart.UserCartRequestDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserEvaluateController {


//
//    @PostMapping(value = "/evaluate/new")
//    public ResponseEntity<Object> postUserEvaluate(@RequestBody UserCartRequestDTO requestDTO){
//        return ResponseEntity.ok(dataReturnService.success(userCartService.deleteCartByUser(requestDTO)));
//    }


}
