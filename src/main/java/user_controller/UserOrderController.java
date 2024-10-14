package user_controller;

import dto.liked_product.LikedProductRequestDTO;
import dto.order.UserOrderRequestDTO;
import dto.order_detail.OrderDetailRequestDTO;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.orders.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UserOrderController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    OrderService orderService;


    @PostMapping(value = "/user/orders/all")
    public ResponseEntity<Object> getAllLikedAll(@RequestBody Optional<HashMap<String, String>> requestDTO) {
        if (requestDTO.isEmpty() || requestDTO.get().get("userLogin") == null) {
            throw new UsernameNotFoundException("Truong du lieu userLogin trong!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getAllOrderByUser( requestDTO.get().get("userLogin"))));
    }


    @PostMapping(value = "/user/orders/new")
    public ResponseEntity<Object> postNewOrder(@RequestBody List<UserOrderRequestDTO> requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(orderService.postNewOrder( requestDTO)));
    }

    @PostMapping(value = "/user/orders/detail")
    public ResponseEntity<Object> detailOrder(@RequestBody Optional<HashMap<String,String>> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get().get("orderCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getOrderById(Long.valueOf(requestDTO.get().get("orderCode")))));
    }


    @PostMapping(value = "/order-confirm/{orderBillCode}")
    public ResponseEntity<Object> postNewOrder(@PathVariable("orderBillCode") String orderBillCode) {
        return ResponseEntity.ok(dataReturnService.success(orderService.confirmOrder(orderBillCode)));
    }


}
