package service.auth_shipper;

import dto.auth_shipper.ShipperLoginResponseDTO;
import dto.shipper.ShipperRequestDTO;
import dto.shipper.ShipperResponseDTO;

public interface ShipperLoginService  {
    ShipperLoginResponseDTO getShipper(String userLogin);
    String getShipperLoginAuthenication();
    String extractShipperLogin(String shipperLogin);
    boolean isValidShipperLogin(String rawShipperLogin);
}
