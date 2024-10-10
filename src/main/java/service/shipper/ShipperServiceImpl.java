package service.shipper;

import dao.ShipperRepository;
import dto.product.ProductMapper;
import dto.role_admin.RoleMapper;
import dto.shipper.ShipperMapper;
import dto.shipper.ShipperRequestDTO;
import dto.shipper.ShipperResponseDTO;
import entity.Product;
import entity.Role;
import entity.Shipper;
import entity.enum_package.ProductStatus;
import entity.enum_package.ShipperStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {
    @Autowired
     ShipperRepository shipperRepository;
    @Override
    public ShipperResponseDTO lockShipper(ShipperRequestDTO request) {
        Shipper shipper = shipperRepository.findById(Integer.valueOf(request.getShipperCode())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        shipper.setShipperStatus(ShipperStatus.inActive);
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public ShipperResponseDTO unlockShipper(ShipperRequestDTO request) {
        Shipper shipper = shipperRepository.findById(Integer.valueOf(request.getShipperCode())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        shipper.setShipperStatus(ShipperStatus.active);
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public ShipperResponseDTO postData(ShipperRequestDTO shipperRequestDTO) {
        Shipper shipper = shipperRepository.save(ShipperMapper.INSTANCE.toShipper(shipperRequestDTO));
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipper);
    }

    @Override
    public ShipperResponseDTO updateData(ShipperRequestDTO shipperRequestDTO) {
        Shipper shipper = shipperRepository.findById(Integer.valueOf(shipperRequestDTO.getShipperCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        shipper.setUpdatedDate(new Date());
        shipper.setName(shipperRequestDTO.getName());
        shipper.setEmail(shipperRequestDTO.getEmail());
        shipper.setPhone(shipperRequestDTO.getPhone());
        shipper.setAddress(shipperRequestDTO.getAddress());
        shipper.setAddressDetail(shipperRequestDTO.getAddressDetail());
        shipper.setPassword(shipperRequestDTO.getPassword());
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public ShipperResponseDTO deleteData(ShipperRequestDTO shipperRequestDTO) {
        Shipper shipper = shipperRepository.findById(Integer.valueOf(shipperRequestDTO.getShipperCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        shipper.setDeleted(true);
        shipper.setDeletedDate(new Date());
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public List<ShipperResponseDTO> getAllData() {
        return ShipperMapper.INSTANCE.toShipperResponseDTO(shipperRepository.findAll());
    }

    @Override
    public ShipperResponseDTO getDetailData(Integer integer) {
        return null;
    }
}
