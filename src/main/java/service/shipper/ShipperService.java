package service.shipper;

import dto.shipper.ShipperRequestDTO;
import dto.shipper.ShipperResponseDTO;
import service.common.CommonService;

public interface ShipperService extends CommonService<ShipperRequestDTO, ShipperResponseDTO,Integer> {
    ShipperResponseDTO lockShipper(ShipperRequestDTO request);

    ShipperResponseDTO unlockShipper(ShipperRequestDTO request);

    ShipperResponseDTO getOrder(ShipperRequestDTO request);

    ShipperResponseDTO getShipperInfo(Long request);

}
