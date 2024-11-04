package admin_controller;

import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.receive_delivery.ReceiveDeliveryService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/admin")
public class WarehouseController {
    @Autowired
    ReceiveDeliveryService receiveDeliveryService;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping(value = "warehouse/add-delivery")
    public ResponseEntity<Object> receiveDeliveryNewList(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addDeliveryToList(Long.valueOf(request.get("shipperCode")),Long.valueOf(request.get("orderCode")))));
    }


    @PostMapping(value = "warehouse/add-receive")
    public ResponseEntity<Object> receiveReceiveNewList(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(Long.valueOf(request.get("shipperCode")),Long.valueOf(request.get("orderCode")))));
    }


    @PostMapping(value = "/warehouse/import-warehouse")
    public ResponseEntity<Object> confirmImport(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(Long.valueOf(request.get("shipperCode")),Long.valueOf(request.get("orderCode")))));
    }
    @PostMapping(value = "/warehouse/import-appoiment")
    public ResponseEntity<Object> importImportAppoiment(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(Long.valueOf(request.get("shipperCode")),Long.valueOf(request.get("orderCode")))));
    }
}
