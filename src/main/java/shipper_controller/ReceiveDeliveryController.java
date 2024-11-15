package shipper_controller;

import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.orders.OrderService;
import service.receive_delivery.ReceiveDeliveryService;
import service.shipper.ShipperService;

@RestController
@RequestMapping(value = "/shipper")
public class ReceiveDeliveryController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ReceiveDeliveryService receiveDeliveryService;

    @Autowired
    ShipperService shipperService;

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/info")
    public ResponseEntity<Object> ShipperInfo(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(shipperService.getShipperInfo(request.getShipperCode())));
    }

    @PostMapping(value = "/receive-delivery/detail")
    public ResponseEntity<Object> OrderInfo(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        if(request.getOrdersCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Order code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(orderService.getShipperOrderById(request.getOrdersCode())));
    }

    @PostMapping(value = "/receive-delivery/all")
    public ResponseEntity<Object> receiveList(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.getListReceiveDelivery(request.getShipperCode())));
    }

    @PostMapping(value = "/receive-delivery/newList")
    public ResponseEntity<Object> receiveDeliveryNewList(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.createListReceiveDelivery(request)));
    }

    @PostMapping(value = "/receive-delivery/complete-delivery")
    public ResponseEntity<Object> receiveDeliveryCompleteDelivery(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getReceiveDeliveryCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("receiveDeliveryCode is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.completeDelivery(request)));
    }
    @PostMapping(value = "/receive-delivery/complete-receive")
    public ResponseEntity<Object> receiveReceiveCompleteDelivery(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getReceiveDeliveryCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("receiveDeliveryCode is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.completeReceive(request)));
    }
    @PostMapping(value = "/receive-delivery/appoiment")
    public ResponseEntity<Object> appoiment(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getReceiveDeliveryCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("receiveDeliveryCode is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.appoimentDelivery(request)));
    }
}
