package service.receive_delivery;

import dao.OrdersRepository;
import dao.ReceiveDeliveryRepository;
import dao.ShipperRepository;
import dto.order.OrderMapper;
import dto.receive_delivery.ReceiveDeliveryMapper;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.receive_delivery.ReceiveDeliveryResponseDTO;
import entity.Orders;
import entity.ReceiveDelivery;
import entity.Shipper;
import entity.enum_package.OrderStatus;
import entity.enum_package.StatusDelivery;
import entity.enum_package.TypeDelivery;
import entity.enum_package.TypeShipper;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private ReceiveDeliveryMapper receiveDeliveryMapper;

    /**
     * @param shipperCode
     * @return
     */
    @Override
    public List<ReceiveDeliveryResponseDTO> getListReceiveDelivery(Long shipperCode) {
        return ReceiveDeliveryMapper.INSTANCE.toList(receiveDeliveryRepository.findAllForshipper(shipperCode));
    }

    @Override
    public ReceiveDeliveryResponseDTO received(ReceiveDeliveryRequestDTO request) {
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("khong thay id cua shipper"));
        receiveDelivery.setStatusDelivery(StatusDelivery.complete);
        receiveDelivery.setDeliveryDate(new Date());
        return receiveDeliveryMapper.toReceiveDeliveryResponseDTO(receiveDeliveryRepository.save(receiveDelivery));
    }

    @Override
    public List<ReceiveDeliveryResponseDTO> createListReceiveDelivery(ReceiveDeliveryRequestDTO request) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Orders> list = OrderMapper.INSTANCE.toOrdersLists(ordersRepository.findTop5ByOrderStatusAndWard( OrderStatus.warehouse,request.getWardCode(),pageable));
        List <ReceiveDelivery> mainList = new ArrayList<>();
        for (Orders order : list) {
            order.setOrderStatus(OrderStatus.delivery);
            ReceiveDelivery receiveDelivery = ReceiveDeliveryMapper.INSTANCE.toReceiveDelivery(request);
            receiveDelivery.setStatusDelivery(StatusDelivery.taking);
            receiveDelivery.setTypeDelivery(TypeDelivery.delivery);
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Date date = java.sql.Date.valueOf(tomorrow);
            receiveDelivery.setDeliveryDate(date);
            receiveDelivery.setOrders(order);
            receiveDelivery.setShipper(shipperRepository.findById(request.getShipperCode()).get());
            mainList.add(receiveDelivery);
        }
         return ReceiveDeliveryMapper.INSTANCE.toList(receiveDeliveryRepository.saveAllAndFlush(mainList));
    }

    @Override
    public ReceiveDeliveryResponseDTO taking(ReceiveDeliveryRequestDTO request) {
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("khong thay id cua shipper"));
        receiveDelivery.setStatusDelivery(StatusDelivery.taking);
        return receiveDeliveryMapper.toReceiveDeliveryResponseDTO(receiveDeliveryRepository.save(receiveDelivery));
    }

    @Override
    public ReceiveDeliveryResponseDTO appoiment(ReceiveDeliveryRequestDTO request) {
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("khong thay id cua shipper"));
        ;
        receiveDelivery.setStatusDelivery(StatusDelivery.taking);
        return receiveDeliveryMapper.toReceiveDeliveryResponseDTO(receiveDeliveryRepository.save(receiveDelivery));
    }

    @Override
    public ReceiveDeliveryResponseDTO pickUpOrders(Long shipperCode,Long receiveDeliveryCode, Long ordersCode ) {
        Orders orders = ordersRepository.findById(ordersCode).orElseThrow(() -> new DataNotFoundException("khong thay id cua don hang"));
        orders.setOrderStatus(OrderStatus.warehouse);
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(receiveDeliveryCode).orElseThrow(() -> new DataNotFoundException("Khong tim thay don hang"));
        receiveDelivery.setStatusDelivery(StatusDelivery.complete);
        receiveDelivery.setDeliveryDate(new Date());
        receiveDelivery.setShipper(shipperRepository.findShipperReceiveByID(shipperCode, TypeShipper.receive));
        receiveDelivery.setOrders(orders);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDelivery);
    }

    @Override
    public ReceiveDeliveryResponseDTO addOderToList(Long shipperCode, Long ordersCode ) {
        Orders orders = ordersRepository.findById(ordersCode).orElseThrow(() -> new DataNotFoundException("khong thay id cua don hang"));
        orders.setOrderStatus(OrderStatus.delivery);
        Shipper shipper = shipperRepository.findById(shipperCode).orElseThrow(() -> new DataNotFoundException("Khong tim thay shipper"));
        ReceiveDelivery receiveDelivery = ReceiveDelivery.builder().shipper(shipper).orders(orders).deliveryDate(new Date()).createdDate(new Date()).statusDelivery(StatusDelivery.taking).typeDelivery(TypeDelivery.delivery).build();
        receiveDeliveryRepository.save(receiveDelivery);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDelivery);
    }
}
