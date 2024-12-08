package admin_controller;

import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.orders.OrderService;

import java.util.HashMap;

@RestController
@RequestMapping(value="/admin")
public class AdminOrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DataReturnService dataReturnService;

    @GetMapping(value = "/order/all")
    public ResponseEntity<Object> getAllRole() {
        return ResponseEntity.ok(dataReturnService.success(orderService.getAllOrder()));
    }

    @PostMapping(value = "/order/get")
    public ResponseEntity<Object> getOrderById(@RequestBody  HashMap<String,Long> request) {
        if(request.isEmpty() || request.get("orderCode") == null){
            throw new DataNotFoundException("Du lieu khong ton tai");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getOrderById(request.get("orderCode"))));
    }

}
