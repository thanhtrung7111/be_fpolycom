package service.import_export_orders;

import dao.ImportExportOrdersRepository;
import dao.OrdersRepository;
import dao.ReceiveDeliveryRepository;
import dao.WarehouseRepository;
import dto.import_export_orders.ImportExportListOrdersRequestDTO;
import dto.import_export_orders.ImportExportOrdersMapper;
import dto.import_export_orders.ImportExportOrdersRequestDTO;
import dto.import_export_orders.ImportExportOrdersResponseDTO;
import entity.ImportExportOrders;
import entity.Orders;
import entity.ReceiveDelivery;
import entity.Warehouse;
import entity.enum_package.OrderStatus;
import entity.enum_package.StatusDelivery;
import entity.enum_package.TypeDelivery;
import entity.enum_package.TypeImportExportOrders;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportExportOrdersServiceImpl implements ImportExportOrdersService {
    @Autowired
    ImportExportOrdersRepository importExportOrdersRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ReceiveDeliveryRepository receiveDeliveryRepository;

    @Override
    public ImportExportOrdersResponseDTO confirmImport(Long warehouseCode, Long ordersCode) {
        Warehouse warehouse = warehouseRepository.findById(warehouseCode).orElseThrow(()-> new DataNotFoundException("Khong tim thay kho"));
        Orders  orders = ordersRepository.findById(ordersCode).orElseThrow(()-> new DataNotFoundException("Khong tim thay don hang"));
        orders.setConfirmWarehouse(true);
        ordersRepository.save(orders);
        ImportExportOrders importExportOrders = ImportExportOrders.builder().typeImportExportOrders(TypeImportExportOrders.importOrders).createdDate(new Date()).date(LocalDateTime.now()).warehouse(warehouse).orders(orders).build();
        return ImportExportOrdersMapper.INSTANCE.toResponse(importExportOrdersRepository.save(importExportOrders));
    }

    @Override
    public ImportExportOrdersResponseDTO confirmImportAppoiment(Long warehouseCode, Long ordersCode) {

        return null;
    }

    @Override
    public ImportExportOrdersResponseDTO confirmExport(Long warehouseCode, Long ordersCode) {
        Warehouse warehouse = warehouseRepository.findById(warehouseCode).orElseThrow(()-> new DataNotFoundException("Khong tim thay kho"));
        Orders  orders = ordersRepository.findById(ordersCode).orElseThrow(()-> new DataNotFoundException("Khong tim thay don hang"));
        ImportExportOrders importExportOrders = ImportExportOrders.builder().typeImportExportOrders(TypeImportExportOrders.exportOrders).createdDate(new Date()).date(LocalDateTime.now()).warehouse(warehouse).orders(orders).build();
        importExportOrdersRepository.save(importExportOrders);
        return ImportExportOrdersMapper.INSTANCE.toResponse(importExportOrders);
    }

    @Override
    public List<ImportExportOrdersResponseDTO> confirmListImport(ImportExportListOrdersRequestDTO request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseCode()).orElseThrow(()-> new DataNotFoundException("Khong tim thay kho"));
        List<Orders> list = ordersRepository.findAllById(request.getOrdersCode());
        List<ImportExportOrders> mainList = new ArrayList<>();
        for(Orders orders : list) {
            ImportExportOrders importExportOrders = ImportExportOrders.builder().typeImportExportOrders(TypeImportExportOrders.importOrders).createdDate(new Date()).date(LocalDateTime.now()).warehouse(warehouse).orders(orders).build();
            importExportOrdersRepository.save(importExportOrders);
            mainList.add(importExportOrders);
            ReceiveDelivery receiveDelivery = ReceiveDelivery.builder().statusDelivery(StatusDelivery.taking).orders(orders).deliveryDate(Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant())).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).typeDelivery(TypeDelivery.delivery).build();
            receiveDeliveryRepository.save(receiveDelivery);
            orders.setConfirmWarehouse(true);
        }
        ordersRepository.saveAll(list);
        return ImportExportOrdersMapper.INSTANCE.toResponseList(mainList);

    }

    @Override
    public List<ImportExportOrdersResponseDTO> confirmListExport(ImportExportListOrdersRequestDTO request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseCode()).orElseThrow(()-> new DataNotFoundException("Khong tim thay kho"));
        List<ImportExportOrders> mainList = new ArrayList<>();
        List<Orders> orders = ordersRepository.findOrdersByOrders(request.getOrdersCode());
        for(Orders order : orders) {
            ImportExportOrders importExportOrders = ImportExportOrders.builder().typeImportExportOrders(TypeImportExportOrders.exportOrders).createdDate(new Date()).date(LocalDateTime.now()).warehouse(warehouse).orders(order).build();
            importExportOrdersRepository.save(importExportOrders);
            mainList.add(importExportOrders);
        }
        return ImportExportOrdersMapper.INSTANCE.toResponseList(mainList);
    }
}
