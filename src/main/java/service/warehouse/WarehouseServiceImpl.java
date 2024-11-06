package service.warehouse;

import dao.WarehouseRepository;
import dto.ward.WardCreateRequestDTO;
import dto.warehouse.WarehouseMapper;
import dto.warehouse.WarehouseRequestDTO;
import dto.warehouse.WarehouseResponseDTO;
import entity.District;
import entity.Province;
import entity.Ward;
import entity.Warehouse;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;


    @Override
    public WarehouseResponseDTO postData(WarehouseRequestDTO request) {
        Warehouse warehouse = WarehouseMapper.INSTANCE.toWarehouse(request);
        return WarehouseMapper.INSTANCE.toWarehouseResponseDTO(warehouseRepository.save(warehouse));
    }

    @Override
    public WarehouseResponseDTO updateData(WarehouseRequestDTO request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay kho"));
        warehouse.setDistrict(District.builder().id(request.getDistrictCode()).build());
        warehouse.setProvince(Province.builder().id(request.getProvinceCode()).build());
        warehouse.setWard(Ward.builder().id(request.getWardCode()).build());
        warehouse.setAddress(request.getAddress());
        warehouse.setAddressDetail(request.getAddressDetail());
        warehouse.setUpdatedDate(new Date());
        return WarehouseMapper.INSTANCE.toWarehouseResponseDTO(warehouseRepository.save(warehouse));
    }

    @Override
    public WarehouseResponseDTO deleteData(WarehouseRequestDTO request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay kho"));
        warehouse.setDeleted(true);
        warehouse.setDeletedDate(new Date());
        return WarehouseMapper.INSTANCE.toWarehouseResponseDTO(warehouseRepository.save(warehouse));
    }

    @Override
    public List<WarehouseResponseDTO> getAllData() {
        return WarehouseMapper.INSTANCE.toList(warehouseRepository.findByDeleted());
    }

    @Override
    public WarehouseResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
