package admin_controller;

import dao.ShipperRepository;
import dto.import_export_orders.ImportExportListOrdersRequestDTO;
import dto.receive_delivery.AddReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryMapper;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.warehouse.WarehouseRequestDTO;
import entity.*;
import entity.enum_package.TypeDelivery;
import entity.enum_package.TypeImportExportOrders;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.data_return.DataReturnService;
import service.import_export_orders.ImportExportOrdersService;
import service.orders.OrderService;
import service.receive_delivery.ReceiveDeliveryService;
import service.warehouse.WarehouseService;

import java.util.Date;
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

    @Autowired
    ShipperRepository shipperRepository;

    @Autowired
    OrderService orderService;

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
        if (errors.hasFieldErrors("warehouseCode")) {
            return ResponseEntity.ok(dataReturnService.dataNotFound("warehouse is empty!!"));
        }
        return ResponseEntity.ok(dataReturnService.success(warehouseService.updateData(request)));
    }

    @PostMapping(value = "/warehouse/delete")
    public ResponseEntity<Object> delete(@RequestBody WarehouseRequestDTO request, Errors errors) {
        if (errors.hasFieldErrors("warehouseCode")) {
            return ResponseEntity.ok(dataReturnService.dataNotFound("warehouse is empty!!"));
        }
        return ResponseEntity.ok(dataReturnService.success(warehouseService.deleteData(request)));
    }

    @PostMapping(value = "/warehouse/add-delivery")
    public ResponseEntity<Object> receiveDeliveryNewList(@RequestBody AddReceiveDeliveryRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addDeliveryToList(request)));
    }

    @GetMapping(value = "/warehouse/all-receive")
    public ResponseEntity<Object> allReceive() {
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.getAllReceiveByTypeDelivery(TypeDelivery.receive)));
    }

    @GetMapping(value = "/warehouse/all-delivery")
    public ResponseEntity<Object> allDelivery() {
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.getAllReceiveByTypeDelivery(TypeDelivery.delivery)));
    }

    @GetMapping(value = "/warehouse/all-refund")
    public ResponseEntity<Object> allRefund() {
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.getAllReceiveByTypeDelivery(TypeDelivery.refund)));
    }





    @PostMapping(value = "/warehouse/add-receive")
    public ResponseEntity<Object> receiveReceiveNewList(@RequestBody AddReceiveDeliveryRequestDTO request) {

        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(request)));
    }

    @PostMapping(value = "/warehouse/import-warehouse")
    public ResponseEntity<Object> confirmImport(@RequestBody ImportExportListOrdersRequestDTO request) {
        if (request.getOrdersCode().isEmpty()) {
            throw new DataNotFoundException("Don hang bi trong!!");
        }
        return ResponseEntity.ok(dataReturnService.success(importExportOrdersService.confirmListImport(request)));
    }

    @PostMapping(value = "/warehouse/confirmDeliverySuccess")
    public ResponseEntity<Object> confirmImport(@RequestBody HashMap<String,Long> request) {
        if (request.isEmpty() || request.get("orderCode") == null) {
            throw new DataNotFoundException("Don hang bi trong!!");
        }
        return ResponseEntity.ok(orderService.confirmOrderPaymentSuccess(request.get("orderCode")));
    }

    @PostMapping(value = "/warehouse/export-warehouse")
    public ResponseEntity<Object> exportWarehouse(@RequestBody ImportExportListOrdersRequestDTO request) {
        if (request.getOrdersCode().isEmpty()) {
            throw new DataNotFoundException("Don hang bi trong!!");
        }
        return ResponseEntity.ok(dataReturnService.success(importExportOrdersService.confirmListExport(request)));
    }

    @PostMapping(value = "/warehouse/cancel")
    public ResponseEntity<Object> cancel(@RequestBody ReceiveDeliveryRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.cancelDelivery(request)));
    }


//    @PostMapping(value = "/warehouse/import-warehouse")
//    public ResponseEntity<Object> confirmImport(@RequestBody HashMap<String,String> request) {
//        if(request.isEmpty()){
//            throw new DataNotFoundException("request  is empty");
//        }
//        return ResponseEntity.ok(dataReturnService.success(importExportOrdersService.confirmListImport(Long.valueOf(request.get("warehouseCode")),Long.valueOf(request.get("ordersCode")))));
//    }
//    @PostMapping(value = "/warehouse/import-appoiment")
//    public ResponseEntity<Object> confirmImportAppoiment(@RequestBody HashMap<String,String> request) {
//        if(request.isEmpty()){
//            throw new DataNotFoundException("Shipper code is empty");
//        }
//        return ResponseEntity.ok(dataReturnService.success(receiveDeliveryService.addReceiveToList(Long.valueOf(request.get("warehouseCode")),Long.valueOf(request.get("ordersCode")))));
//    }
//    @PostMapping(value = "/warehouse/export-warehouse")
//    public ResponseEntity<Object> exportWarehouse(@RequestBody HashMap<String,String> request) {
//        if(request.isEmpty()){
//            throw new DataNotFoundException("request is empty");
//        }
//        return ResponseEntity.ok(dataReturnService.success(importExportOrdersService.confirmExport(Long.valueOf(request.get("warehouseCode")),Long.valueOf(request.get("ordersCode")))));
//    }

}
