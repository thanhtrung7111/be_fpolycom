package service.receive_delivery;

import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;

import java.util.List;

public interface ReceiveDeliveryService{
    public List<ReceiveDeliveryResponseDTO> getListReceiveDelivery(Long shipperCode);

    public ReceiveDeliveryResponseDTO received(ReceiveDeliveryRequestDTO dto);

    public List<ReceiveDeliveryResponseDTO> createListReceiveDelivery(ReceiveDeliveryRequestDTO request);

    public ReceiveDeliveryResponseDTO taking(ReceiveDeliveryRequestDTO dto);

    public ReceiveDeliveryResponseDTO appoiment(ReceiveDeliveryRequestDTO dto);

    public ReceiveDeliveryResponseDTO pickUpOrders(Long shipperCode,Long receiveDeliveryCode, Long ordersCode );

    public ReceiveDeliveryResponseDTO addOderToList(Long shipperCode, Long ordersCode );
}
