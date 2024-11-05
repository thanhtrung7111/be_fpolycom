package service.import_export_orders;

import dao.ImportExportOrdersRepository;
import dao.OrdersRepository;
import dao.WarehouseRepository;
import dto.import_export_orders.ImportExportOrdersMapper;
import dto.import_export_orders.ImportExportOrdersRequestDTO;
import dto.import_export_orders.ImportExportOrdersResponseDTO;
import entity.ImportExportOrders;
import entity.Orders;
import entity.Warehouse;
import entity.enum_package.TypeImportExportOrders;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ImportExportOrdersServiceImpl implements ImportExportOrdersService {
    @Autowired
    ImportExportOrdersRepository importExportOrdersRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public ImportExportOrdersResponseDTO confirmImport(Long warehouseCode, Long ordersCode) {
        Warehouse warehouse = warehouseRepository.findById(warehouseCode).orElseThrow(()-> new DataNotFoundException("Khong tim thay kho"));
        Orders  orders = ordersRepository.findById(ordersCode).orElseThrow(()-> new DataNotFoundException("Khong tim thay don hang"));
        ImportExportOrders importExportOrders = ImportExportOrders.builder().typeImportExportOrders(TypeImportExportOrders.importOrders).createdDate(new Date()).date(LocalDateTime.now()).warehouse(warehouse).orders(orders).build();
        importExportOrdersRepository.save(importExportOrders);
        return ImportExportOrdersMapper.INSTANCE.toResponse(importExportOrders);
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
}
