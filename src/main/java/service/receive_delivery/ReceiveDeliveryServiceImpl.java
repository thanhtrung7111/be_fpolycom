package service.receive_delivery;

import dao.OrdersRepository;
import dao.ReceiveDeliveryRepository;
import dao.ShipperRepository;
import dto.order.OrderMapper;
import dto.receive_delivery.*;
import entity.NotifycationUser;
import entity.Orders;
import entity.ReceiveDelivery;
import entity.Shipper;
import entity.enum_package.*;
import exeception_handler.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import security.AsyncUpdate;
import service.user_notify.UserNotifyService;

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

    @Autowired
    UserNotifyService userNotifyService;

    @Autowired
    AsyncUpdate asyncUpdate;

    /**
     * @param shipperCode
     * @return
     */
    @Override
    public List<ReceiveDeliveryShipperResponse> getListReceiveDelivery(Long shipperCode) {
        return ReceiveDeliveryMapper.INSTANCE.toShipperResponseList(receiveDeliveryRepository.findAllForshipper(shipperCode));
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
        if(orders.getPaymentReceiptList().size() >0){
            orders.setOrderStatus(OrderStatus.complete);
            userNotifyService.sendNotifyToUser("Đơn hàng "+orders.getId()+" đang giao!","Đơn hàng đang được giao đến bạn, vui lòng để ý điện thoại!",orders.getId().toString(), TypeNotifycationUser.order,orders.getOrderDetailList().get(0).getProductDetail().getImage(),orders.getUserAccount().getId());
            asyncUpdate.updatePaymentWalletStore(orders);
        }
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("khong thay id cua shipper"));
        receiveDelivery.setStatusDelivery(StatusDelivery.complete);
        ordersRepository.save(orders);
        receiveDeliveryRepository.save(receiveDelivery);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDelivery);
    }

    @Override
    public List<ReceiveDeliveryResponseDTO> createListReceiveDelivery(ReceiveDeliveryRequestDTO request) {
        Pageable pageable = PageRequest.of(0, 5);

        List<Orders> list = ordersRepository.findTop5ByOrderStatusAndWard(OrderStatus.warehouse, request.getWardCode(), pageable);

        Shipper shipper = shipperRepository.findById(request.getShipperCode()).orElseThrow(() -> new EntityNotFoundException("Shipper not found"));

        List<ReceiveDelivery> mainList = new ArrayList<>();

        for (Orders order : list) {
            order.setOrderStatus(OrderStatus.delivery);

            ReceiveDelivery receiveDelivery = ReceiveDeliveryMapper.INSTANCE.toReceiveDelivery(request);
            receiveDelivery.setStatusDelivery(StatusDelivery.taking);
            receiveDelivery.setTypeDelivery(TypeDelivery.delivery);
            receiveDelivery.setDeliveryDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));
            receiveDelivery.setOrders(order);
            receiveDelivery.setShipper(shipper);

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
        List<ReceiveDelivery> list = receiveDeliveryRepository.findAllById(request.getReceiveCodes());
        Shipper shipper = shipperRepository.findById(request.getShipperCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay shipper"));
        if(list.isEmpty()) System.out.println("list rong");
        for (ReceiveDelivery receiveDelivery : list) {
            Orders order = receiveDelivery.getOrders();
            order.setOrderStatus(OrderStatus.delivery);
            userNotifyService.sendNotifyToUser("Đơn hàng #"+order.getId()+" đang giao!","Đơn hàng đang được giao đến bạn, vui lòng để ý điện thoại!",order.getId().toString(), TypeNotifycationUser.order,order.getOrderDetailList().get(0).getProductDetail().getImage(),order.getUserAccount().getId());
            ordersRepository.save(order);
           receiveDelivery.setShipper(shipper);
        }
        receiveDeliveryRepository.saveAll(list);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDtoList(list);
    }

    @Override
    public List<ReceiveDeliveryResponseDTO> addReceiveToList(AddReceiveDeliveryRequestDTO request) {
        List<ReceiveDelivery> list = receiveDeliveryRepository.findAllById(request.getReceiveCodes());
        Shipper shipper = shipperRepository.findById(request.getShipperCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay shipper"));

        if(list.isEmpty()) System.out.println("list rong");
        for (ReceiveDelivery receiveDelivery : list) {
            receiveDelivery.setShipper(shipper);
        }
        receiveDeliveryRepository.saveAll(list);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDtoList(list);
    }

    @Override
    public ReceiveDeliveryResponseDTO cancelDelivery(ReceiveDeliveryRequestDTO request) {
        ReceiveDelivery receiveDelivery = receiveDeliveryRepository.findById(request.getReceiveDeliveryCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay receive"));
        receiveDelivery.setStatusDelivery(StatusDelivery.failed);
        Orders orders = ordersRepository.findById(receiveDelivery.getOrders().getId()).orElseThrow(() -> new DataNotFoundException("Khong tim thay orders"));
        orders.setOrderStatus(OrderStatus.cancel);
        ReceiveDelivery returnReceiveDelivery = ReceiveDelivery.builder().statusDelivery(StatusDelivery.taking).typeDelivery(TypeDelivery.refund).deliveryDate(new Date()).createdDate(new Date()).orders(orders).build();
        ordersRepository.save(orders);
        receiveDeliveryRepository.save(receiveDelivery);
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDTO(receiveDeliveryRepository.save(returnReceiveDelivery));

    }

    @Override
    public List<ReceiveDeliveryResponseDTO> getAllReceiveByTypeDelivery(TypeDelivery typeDelivery) {
        return ReceiveDeliveryMapper.INSTANCE.toReceiveDeliveryResponseDtoList(receiveDeliveryRepository.findAllByTypeDelivery(typeDelivery));
    }


}
