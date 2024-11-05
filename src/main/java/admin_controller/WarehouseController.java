package admin_controller;

import dao.ImportExportOrdersRepository;
import dto.ward.WardCreateRequestDTO;
import dto.warehouse.WarehouseRequestDTO;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.import_export_orders.ImportExportOrdersService;
import service.receive_delivery.ReceiveDeliveryService;
import service.warehouse.WarehouseService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/admin")
public class WarehouseController {
    @Autowired
    ReceiveDeliveryService receiveDeliveryService;

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    ImportExportOrdersService importExportOrdersService;

    @GetMapping(value = "/warehouse/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(dataReturnService.success(warehouseService.getAllData()));
    }

    @PostMapping(value = "/warehouse/new")
    public ResponseEntity<Object> newWard(@RequestBody WarehouseRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(warehouseService.postData(request)));
    }
    @PostMapping(value = "/warehouse/update")
    public ResponseEntity<Object> update(@RequestBody WarehouseRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("warehouseCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("warehouse is empty!!"));
        }
        return ResponseEntity.ok(dataReturnService.success(warehouseService.updateData(request)));
    }
    @PostMapping(value = "/warehouse/delete")
    public ResponseEntity<Object> delete(@RequestBody WarehouseRequestDTO request, Errors errors) {
        if(errors.hasFieldErrors("warehouseCode")){
            return ResponseEntity.ok(dataReturnService.dataNotFound("warehouse is empty!!"));
        }
        return ResponseEntity.ok(dataReturnService.success(warehouseService.deleteData(request)));
    }

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
        return ResponseEntity.ok(dataReturnService.success(importExportOrdersService.confirmImport(Long.valueOf(request.get("warehouseCode")),Long.valueOf(request.get("ordersCode")))));
    }
//    @PostMapping(value = "/warehouse/import-appoiment")
//    public ResponseEntity<Object> confirmImportAppoiment(@RequestBody HashMap<String,String> request) {
//        if(request.isEmpty()){
//            throw new DataNotFoundException("Shipper code is empty");
//        }
//        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(Long.valueOf(request.get("warehouseCode")),Long.valueOf(request.get("ordersCode")))));
//    }
    @PostMapping(value = "/warehouse/export-warehouse")
    public ResponseEntity<Object> exportWarehouse(@RequestBody HashMap<String,String> request) {
        if(request.isEmpty()){
            throw new DataNotFoundException("Shipper code is empty");
        }
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(Long.valueOf(request.get("warehouseCode")),Long.valueOf(request.get("ordersCode")))));
    }
}
