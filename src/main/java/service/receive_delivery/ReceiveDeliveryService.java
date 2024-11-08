package service.receive_delivery;

import dto.receive_delivery.AddReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;
import dto.receive_delivery.ReceiveDeliveryShipperResponse;

import java.util.List;

public interface ReceiveDeliveryService{
    public List<ReceiveDeliveryShipperResponse> getListReceiveDelivery(Long shipperCode);

    public ReceiveDeliveryResponseDTO completeReceive(ReceiveDeliveryRequestDTO dto);

    public default ReceiveDeliveryResponseDTO completeDelivery(ReceiveDeliveryRequestDTO dto) {
        return null;
    }

    public List<ReceiveDeliveryResponseDTO> createListReceiveDelivery(ReceiveDeliveryRequestDTO request);


    public ReceiveDeliveryResponseDTO appoimentDelivery(ReceiveDeliveryRequestDTO dto);



    public ReceiveDeliveryResponseDTO pickUpOrders(Long shipperCode,Long receiveDeliveryCode, Long ordersCode );

    public List<ReceiveDeliveryResponseDTO> addDeliveryToList(AddReceiveDeliveryRequestDTO request);

    public List<ReceiveDeliveryResponseDTO> addReceiveToList(AddReceiveDeliveryRequestDTO request );

    public ReceiveDeliveryResponseDTO cancelDelivery(ReceiveDeliveryRequestDTO request);


}
