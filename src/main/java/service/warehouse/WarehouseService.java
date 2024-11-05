package service.warehouse;

import dto.ward.WardCreateRequestDTO;
import dto.warehouse.WarehouseRequestDTO;
import dto.warehouse.WarehouseResponseDTO;
import entity.Warehouse;
import service.common.CommonService;

public interface WarehouseService extends CommonService<WarehouseRequestDTO, WarehouseResponseDTO, Long> {
    
}
