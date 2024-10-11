package user_controller;

import dto.liked_product.LikedProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.orders.OrderService;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserOrderController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    OrderService orderService;


    @PostMapping(value = "/orders/all")
    public ResponseEntity<Object> getAllLikedAll(@RequestBody Optional<HashMap<String, String>> requestDTO) {
        if (requestDTO.isEmpty() || requestDTO.get().get("userLogin") == null) {
            throw new UsernameNotFoundException("Truong du lieu userLogin trong!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getAllOrderByUser( requestDTO.get().get("userLogin"))));
    }
}
