package service.receive_delivery;

import dao.OrdersRepository;
import dao.ReceiveDeliveryRepository;
import dao.ShipperRepository;
import dto.order.OrderMapper;
import dto.receive_delivery.*;
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

    /**
     * @param shipperCode
     * @return
     */
    @Override
    public List<ReceiveDeliveryResponseDTO> getListReceiveDelivery(Long shipperCode) {
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDtoList(receiveDeliveryRepository.findAllForshipper(shipperCode));
    }

    @Override
    public ReceiveDeliveryResponseDTO completeReceive(ReceiveDeliveryRequestDTO request) {
        Orders orders = ordersRepository.findById(request.getOrdersCode()).orElseThrow(() -> new DataNotFoundException("Orders not found"));
        orders.setOrderStatus(OrderStatus.warehouse);
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("khong thay id cua shipper"));
        receiveDelivery.setStatusDelivery(StatusDelivery.complete);
        receiveDeliveryRepository.save(receiveDelivery);
        ordersRepository.save(orders);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDelivery);
    }

    @Override
    public ReceiveDeliveryResponseDTO completeDelivery(ReceiveDeliveryRequestDTO request) {
        Orders orders = ordersRepository.findById(request.getOrdersCode()).orElseThrow(() -> new DataNotFoundException("Orders not found"));
        orders.setOrderStatus(OrderStatus.complete);
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("khong thay id cua shipper"));
        receiveDelivery.setStatusDelivery(StatusDelivery.complete);
        ordersRepository.save(orders);
        receiveDeliveryRepository.save(receiveDelivery);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDelivery);
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
         return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDtoList(receiveDeliveryRepository.saveAllAndFlush(mainList));
    }

    @Override
    public ReceiveDeliveryResponseDTO appoimentDelivery(ReceiveDeliveryRequestDTO request) {
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("ReceiveDelivery not found"));
        receiveDelivery.setStatusDelivery(StatusDelivery.appoinment);
        Orders orders = ordersRepository.findById(request.getOrdersCode()).orElseThrow(() -> new DataNotFoundException("Orders not found"));
        orders.setOrderStatus(OrderStatus.warehouse);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDeliveryRepository.save(receiveDelivery));
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
        ordersRepository.save(orders);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDeliveryRepository.save(receiveDelivery));
    }

    @Override
    public List<ReceiveDeliveryResponseDTO> addDeliveryToList(AddReceiveDeliveryRequestDTO request) {
        List<Orders> list = ordersRepository.findOrdersByOrders(request.getOrdersCode());
        Shipper shipper = shipperRepository.findById(request.getShipperCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay shipper"));
        List<ReceiveDelivery> mainList = new ArrayList<>();
        if(list.isEmpty()) System.out.println("list rong");
        for (Orders orders : list) {
            orders.setOrderStatus(OrderStatus.delivery);
            ReceiveDelivery receiveDelivery = ReceiveDelivery.builder().shipper(shipper).orders(orders).deliveryDate(new Date()).createdDate(new Date()).statusDelivery(StatusDelivery.taking).typeDelivery(TypeDelivery.delivery).build();
            receiveDeliveryRepository.save(receiveDelivery);
            ordersRepository.save(orders);
            mainList.add(receiveDelivery);
        }
        return ReceiveDeliveryMapper.INSTANCE.toList(mainList);
    }

    @Override
    public List<ReceiveDeliveryResponseDTO> addReceiveToList(AddReceiveDeliveryRequestDTO request) {
        List<Orders> list = ordersRepository.findOrdersByOrders(request.getOrdersCode());
        Shipper shipper = shipperRepository.findById(request.getShipperCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay shipper"));
        List<ReceiveDelivery> mainList = new ArrayList<>();
        if(list.isEmpty()) System.out.println("list rong");
        for (Orders order : list) {
            ReceiveDelivery receiveDelivery = ReceiveDelivery.builder().shipper(shipper).orders(order).deliveryDate(new Date()).createdDate(new Date()).statusDelivery(StatusDelivery.taking).typeDelivery(TypeDelivery.receive).build();
            receiveDeliveryRepository.save(receiveDelivery);
            ordersRepository.save(order);
            mainList.add(receiveDelivery);
        }
        return ReceiveDeliveryMapper.INSTANCE.toList(mainList);
    }


}
