package admin_controller;

import dto.product.ProductApproveRequestDTO;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.role_admin.RoleRequestDTO;
import dto.shipper.ShipperRequestDTO;
import exeception_handler.DataNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.receive_delivery.ReceiveDeliveryService;
import service.shipper.ShipperService;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value="/admin")
public class ShipperController {
    @Autowired
    ShipperService shipperService;
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    ReceiveDeliveryService receiveDeliveryService;

    @GetMapping(value = "/shipper/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(shipperService.getAllData()));
    }

    @PostMapping("/shipper/lock")
    public ResponseEntity<DataReturn> lockShipper(@RequestBody ShipperRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(shipperService.lockShipper(request)));
    }
    @PostMapping("/shipper/unlock")
    public ResponseEntity<DataReturn> unlockShipper(@RequestBody ShipperRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(shipperService.unlockShipper(request)));
    }
    @PostMapping(value = "/shipper/new")
    public ResponseEntity<Object> newShipper( @RequestBody ShipperRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("userLogin")&&(errors.hasFieldErrors("passord")) ){
            return ResponseEntity.ok(dataReturnService.dataNotFound("userLogin or passord  is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(shipperService.postData(request)));
    }


    @PostMapping(value = "/shipper/update")
    public ResponseEntity<Object> updateShipper(@RequestBody ShipperRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("shipperCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("shipperCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(shipperService.updateData(request)));
    }

    @PostMapping(value = "/shipper/delete")
    public ResponseEntity<Object> deleteShipper(@RequestBody ShipperRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("shipperCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("shipperCode is empty"));
        }
        return ResponseEntity.ok(dataReturnService.success(shipperService.deleteData(request)));
    }

    @PostMapping(value = "shipper/newList")
    public ResponseEntity<Object> receiveDeliveryNewList(@RequestBody ReceiveDeliveryRequestDTO request) {
        if(request.getShipperCode().describeConstable().isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.createListReceiveDelivery(request)));
    }


}
