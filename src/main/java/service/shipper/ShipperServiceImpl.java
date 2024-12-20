package service.shipper;

import dao.*;
import dto.shipper.ShipperMapper;
import dto.shipper.ShipperRequestDTO;
import dto.shipper.ShipperResponseDTO;
import entity.*;
import entity.enum_package.ShipperStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    ShipperRepository shipperRepository;

    @Autowired
    private ShipperMapper shipperMapper;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    WardRepository wardRepository;

    @Override
    public ShipperResponseDTO lockShipper(ShipperRequestDTO request) {
        Shipper shipper = shipperRepository.findById(Long.valueOf(request.getShipperCode())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        shipper.setShipperStatus(ShipperStatus.inActive);
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public ShipperResponseDTO unlockShipper(ShipperRequestDTO request) {
        Shipper shipper = shipperRepository.findById(Long.valueOf(request.getShipperCode())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        shipper.setShipperStatus(ShipperStatus.active);
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public ShipperResponseDTO getOrder(ShipperRequestDTO request) {

        return null;
    }

    @Override
    public ShipperResponseDTO getShipperInfo(Long request) {
        Shipper shipper = shipperRepository.findById(request).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipper);
    }

    @Override
    public ShipperResponseDTO postData(ShipperRequestDTO shipperRequestDTO) {
        Shipper shipper = shipperMapper.INSTANCE.toShipper(shipperRequestDTO);
        shipper.setPassword(new BCryptPasswordEncoder().encode(shipper.getPassword()));
         shipper = shipperRepository.save(shipper);
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipper);
    }

    @Override
    public ShipperResponseDTO updateData(ShipperRequestDTO shipperRequestDTO) {
        Shipper shipper = shipperRepository.findById(Long.valueOf(shipperRequestDTO.getShipperCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        shipper.setUpdatedDate(new Date());
        shipper.setName(shipperRequestDTO.getName());
        shipper.setUserLogin(shipperRequestDTO.getUserLogin());
        shipper.setPassword(new BCryptPasswordEncoder().encode(shipperRequestDTO.getPassword()) );
        shipper.setAddressDetail(shipperRequestDTO.getAddressDetail());
        shipper.setAddress(shipperRequestDTO.getAddress());
        shipper.setPhone(shipperRequestDTO.getPhone());
        shipper.setEmail(shipperRequestDTO.getEmail());

        //Lấy mã province mới từ request
       Province province = provinceRepository.findById(Long.valueOf(shipperRequestDTO.getProvinceCode()))
                .orElseThrow(() -> new DataNotFoundException("Province not found"));
        shipper.setProvince(province);

        // Lấy đối tượng District từ mã districtCode trong request
        District district = districtRepository.findById(Long.valueOf(shipperRequestDTO.getDistrictCode()))
                .orElseThrow(() -> new DataNotFoundException("District not found"));
        shipper.setDistrict(district);
        // Lấy đối tượng Ward từ mã wardCode trong request
        Ward ward = wardRepository.findById(Long.valueOf(shipperRequestDTO.getWardCode()))
                .orElseThrow(() -> new DataNotFoundException("Ward not found"));
        shipper.setWard(ward);
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipperRepository.save(shipper));
    }

    @Override
    public ShipperResponseDTO deleteData(ShipperRequestDTO shipperRequestDTO) {
        Shipper shipper = shipperRepository.findById(Long.valueOf(shipperRequestDTO.getShipperCode())).orElseThrow(()->new DataNotFoundException("data not found"));
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
