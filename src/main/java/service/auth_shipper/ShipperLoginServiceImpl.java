package service.auth_shipper;

import dao.ShipperRepository;
import dto.auth_shipper.ShipperLoginMapper;
import dto.auth_shipper.ShipperLoginResponseDTO;
import dto.shipper.ShipperMapper;
import entity.Shipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.common.EncodingService;

@Service
public class ShipperLoginServiceImpl implements ShipperLoginService{
    @Autowired
    EncodingService encodingService;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;
    @Autowired
    ShipperRepository shipperRepository;
    @Override
    public ShipperLoginResponseDTO getShipper(String userLogin) {
        Shipper shipper = shipperRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new UsernameNotFoundException("Shipper không tồn tại!"));
        shipper.setUserLogin(encodingService.encode(shipper.getUserLogin()));
        return ShipperLoginMapper.INSTANCE.toShipperLoginResponseDto(shipper);
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
}
