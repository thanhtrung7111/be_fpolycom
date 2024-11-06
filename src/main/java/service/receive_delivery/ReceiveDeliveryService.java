package service.receive_delivery;

import dto.receive_delivery.AddReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;

import java.util.List;

public interface ReceiveDeliveryService{
    public List<ReceiveDeliveryResponseDTO> getListReceiveDelivery(Long shipperCode);

    public ReceiveDeliveryResponseDTO completeReceive(ReceiveDeliveryRequestDTO dto);

    public ReceiveDeliveryResponseDTO completeDelivery(ReceiveDeliveryRequestDTO dto);

    public List<ReceiveDeliveryResponseDTO> createListReceiveDelivery(ReceiveDeliveryRequestDTO request);


    public ReceiveDeliveryResponseDTO appoimentDelivery(ReceiveDeliveryRequestDTO dto);



    public ReceiveDeliveryResponseDTO pickUpOrders(Long shipperCode,Long receiveDeliveryCode, Long ordersCode );

    public List<ReceiveDeliveryResponseDTO> addDeliveryToList(AddReceiveDeliveryRequestDTO request);

    public List<ReceiveDeliveryResponseDTO> addReceiveToList(AddReceiveDeliveryRequestDTO request );


}
