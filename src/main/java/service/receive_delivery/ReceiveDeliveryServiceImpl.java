package service.receive_delivery;

import dao.OrdersRepository;
import dao.ReceiveDeliveryRepository;
import dao.ShipperRepository;
import dto.receive_delivery.ReceiveDeliveryMapper;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;
import entity.Orders;
import entity.ReceiveDelivery;
import entity.enum_package.OrderStatus;
import entity.enum_package.StatusDelivery;
import entity.enum_package.TypeDelivery;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import service.shipper.ShipperService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiveDeliveryServiceImpl implements ReceiveDeliveryService {
    @Autowired
    ReceiveDeliveryRepository receiveDeliveryRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ShipperRepository shipperRepository;

    /**
     * @param shipperCode
     * @return
     */
    @Override
    public List<ReceiveDeliveryResponseDTO> getListReceiveDelivery(Long shipperCode) {
        return ReceiveDeliveryMapper.INSTANCE.toList(receiveDeliveryRepository.findAllForshipper(shipperCode));
    }

    @Override
    public ReceiveDeliveryResponseDTO received(ReceiveDeliveryRequestDTO dto) {
        return null;
    }

    @Override
    public List<ReceiveDeliveryResponseDTO> createListReceiveDelivery(ReceiveDeliveryRequestDTO request) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Orders> list = ReceiveDeliveryMapper.INSTANCE.toListOrders(ordersRepository.findTop5ByOrderStatusAndWard( OrderStatus.complete,request.getWardCode(),pageable));
        System.out.println(list.get(0).getWard());
        List <ReceiveDeliveryResponseDTO> mainList = new ArrayList<>();
        for (Orders order : list) {
            order.setOrderStatus(OrderStatus.delivery);
            ReceiveDelivery receiveDelivery = ReceiveDeliveryMapper.INSTANCE.toReceiveDelivery(request);
            receiveDelivery.setStatusDelivery(StatusDelivery.taking);
            receiveDelivery.setTypeDelivery(TypeDelivery.delivery);
            receiveDelivery.setDeliveryDate(new Date());
            receiveDelivery.setOrders(order);
            receiveDelivery.setShipper(shipperRepository.findById(request.getShipperCode()).get());
            mainList.add(ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDelivery));
        }
         return mainList;
    }

    @Override
    public ReceiveDeliveryResponseDTO taking(ReceiveDeliveryRequestDTO dto) {
        return null;
    }

    @Override
    public ReceiveDeliveryResponseDTO appoiment(ReceiveDeliveryRequestDTO dto) {
        return null;
    }
}
