package store_controller;

import dto.order.UserOrderRequestDTO;
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
@RequestMapping(value = "/store")
public class StoreOrderController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/orders/all")
    public ResponseEntity<Object> getAllOrderByStore(@RequestBody HashMap<String, String> requestDTO) {
        if (requestDTO.isEmpty() || requestDTO.get("storeCode").isBlank()) {
            throw new UsernameNotFoundException("Truong du lieu storeCode trong!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getAllOrderByStore(Long.valueOf( requestDTO.get("storeCode")))));
    }



    @PostMapping(value = "/orders/detail")
    public ResponseEntity<Object> detailOrder(@RequestBody HashMap<String,String> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("orderCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getOrderById(Long.valueOf(requestDTO.get("orderCode")))));
    }

    @PostMapping(value = "/orders/confirm")
    public ResponseEntity<Object> confirmOrder(@RequestBody HashMap<String,String> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("orderCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.confirmOrderByStore(Long.valueOf(requestDTO.get("orderCode")))));
    }
    @PostMapping(value = "/orders/prepare")
    public ResponseEntity<Object> prepareOrder(@RequestBody HashMap<String,String> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("orderCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.prepareReceiveOrders(Long.valueOf(requestDTO.get("orderCode")))));
    }
    @PostMapping(value = "/orders/pick-up")
    public ResponseEntity<Object> pickUpOrder(@RequestBody HashMap<String,String> requestDTO) {
        if(requestDTO.isEmpty() || requestDTO.get("orderCode").isBlank()){
            throw new DataNotFoundException("Du lieu khong ton tai!");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.pickUpReceiveOrders(Long.valueOf(requestDTO.get("orderCode")))));
    }


}
