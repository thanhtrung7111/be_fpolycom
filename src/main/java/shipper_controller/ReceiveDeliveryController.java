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
import service.receive_delivery.ReceiveDeliveryService;

@RestController
@RequestMapping(value = "/shipper")
public class ReceiveDeliveryController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ReceiveDeliveryService receiveDeliveryService;

    @PostMapping(value = "/receive-delivery/all")
    public ResponseEntity<Object> receiveDelivery(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.getListReceiveDelivery(request.getShipperCode())));
    }

    @PostMapping(value = "receive-delivery/newList")
    public ResponseEntity<Object> receiveDeliveryNewList(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.createListReceiveDelivery(request)));
    }

    
}
