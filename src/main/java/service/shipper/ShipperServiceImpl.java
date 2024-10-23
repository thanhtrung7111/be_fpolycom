package service.shipper;

import dao.*;
import dto.product.ProductMapper;
import dto.role_admin.RoleMapper;
import dto.shipper.ShipperMapper;
import dto.shipper.ShipperRequestDTO;
import dto.shipper.ShipperResponseDTO;
import entity.*;
import entity.enum_package.ProductStatus;
import entity.enum_package.ShipperStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import service.common.EncodingService;

import java.util.Date;
import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {
    @Autowired
    EncodingService encodingService;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;
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
    public ShipperResponseDTO getShipper(String userLogin) {
        Shipper shipper = shipperRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new UsernameNotFoundException("Shipper không tồn tại!"));
        shipper.setUserLogin(encodingService.encode(shipper.getUserLogin()));
        return ShipperMapper.INSTANCE.toShipperResponseDto(shipper);
    }

    @Override
    public String getShipperLoginAuthenication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        throw new UsernameNotFoundException("Shipper chưa xác thực!");
    }

    @Override
    public String extractShipperLogin(String shipperLogin) {
        return encodingService.decode(shipperLogin);
    }

    @Override
    public boolean isValidShipperLogin(String rawShipperLogin) {
        return extractShipperLogin(rawShipperLogin).equals(getShipperLoginAuthenication());
    }


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
