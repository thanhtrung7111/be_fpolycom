package service.orders;

import dao.*;
import dto.order.*;
import dto.product.ProductMapper;
import dto.receive_delivery.ReceiveDeliveryMapper;
import entity.*;
import entity.enum_package.*;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.AsyncUpdate;
import service.auth_store.AuthStoreService;
import service.auth_user.AuthUserService;
import service.user_notify.UserNotifyService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PaymenReceiptRepository paymenReceiptRepository;

    @Autowired
    AsyncUpdate asyncUpdate;

    @Autowired
    AuthStoreService authStoreService;

    @Autowired
    ReceiveDeliveryRepository receiveDeliveryRepository;


    @Autowired
    UserNotifyService userNotifyService;

    @Autowired
    ImportExportOrdersRepository importExportOrdersRepository;

    @Override
    public List<OrderResponseDTO> getAllOrderByUser(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        return OrderMapper.INSTANCE.toOrderResponseDtoList(ordersRepository.findAllOrdersByUser(userLoginExtract));
    }

    @Override
    public List<OrderResponseDTO> getAllOrder() {
        return OrderMapper.INSTANCE.toOrderResponseDtoList(ordersRepository.findAll());
    }

    @Override
    public List<OrderInfoResponseDTO> postNewOrder(List<UserOrderRequestDTO> userOrderRequestDTO) {
        if (!userOrderRequestDTO.isEmpty()) {
            if (!authUserService.isValidUserLogin(userOrderRequestDTO.getFirst().getUserLogin())) {
                throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
            }

            String userLoginExtract = authUserService.extractUserlogin(userOrderRequestDTO.getFirst().getUserLogin());
            UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Nguoi dung khong ton tai!"));
            List<Orders> listConvert = OrderMapper.INSTANCE.toOrdersList(userOrderRequestDTO);

            listConvert.forEach(item -> {
                System.out.println(item.getOrderDetailList().size());
                item.setUserAccount(userAccount);
                item.setOrderStatus(OrderStatus.pending);
                item.getOrderDetailList().forEach(i -> i.setOrders(item));
                item.setCreatedDate(new Date());
                item.setUpdatedDate(null);
                item.setDeleted(false);
                item.setDeletedDate(null);
                if (!(item.getVoucherApplyList() == null) && !(item.getVoucherApplyList().size() == 0)) {
                    item.getVoucherApplyList().forEach(i -> {
                        i.setOrders(item);
                        asyncUpdate.updateVoucherUser(userLoginExtract, i.getVoucher().getId());
                    });
                }
            });
            ordersRepository.saveAll(listConvert);
            return OrderMapper.INSTANCE.toOrderInfoResponseDtoList(listConvert);
        }
        throw new DataNotFoundException("Du lieu khogn ton tai!");

    }

    @Override
    public List<OrderInfoResponseDTO> confirmOrder(String orderBillCode) {
        List<Orders> ordersList = ordersRepository.findAllOrdersByOrderBill(orderBillCode);
        if (ordersList.isEmpty()) {
            throw new DataNotFoundException("Khong co don dat hang nao");
        }
        ordersList.forEach(item -> {
            PaymentReceipt paymentReceipt = PaymentReceipt.builder().paymentType(item.getPaymentType()).finalTotal(item.getFinalTotal()).totalAmount(item.getTotalAmount()).totalAmountShip(item.getTotalAmountShip()).totalAmountVoucher(item.getTotalAmountVoucher()).totalAmountDiscount(item.getTotalAmountDiscount()).totalAmountPaid(item.getFinalTotal()).orders(item).createdDate(new Date()).deleted(false).updatedDate(null).deletedDate(null).build();
            PaymentReceipt saved = paymenReceiptRepository.save(paymentReceipt);
            item.getPaymentReceiptList().add(saved);
        });
        ordersList.stream().forEach(item->{

            userNotifyService.sendNotifyToUser("Thanh toán đơn hàng #"+item.getId(),"Thanh toán thành công đơn hàng #"+item.getId(),item.getId().toString(), TypeNotifycationUser.order,item.getOrderDetailList().get(0).getProductDetail().getImage(), item.getUserAccount().getId());
        });
        asyncUpdate.updateProductDetail(ordersList);
        return OrderMapper.INSTANCE.toOrderInfoResponseDtoList(ordersList);
    }

    @Override
    public List<OrderResponseDTO> getAllOrderByStore(Long storeCode) {
        if (authStoreService.isValidStore(storeCode)) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        return OrderMapper.INSTANCE.toOrderResponseDtoList(ordersRepository.findAllOrdersByStore(storeCode));
    }

    @Override
    public OrderInfoResponseDTO getOrderById(Long orderCode) {
        return OrderMapper.INSTANCE.toOrderInfoResponseDto(ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!")));
    }

    @Override
    public OrderShipperResponseDTO getShipperOrderById(Long orderCode) {
        return OrderMapper.INSTANCE.toOrderShipperResponseDTO(ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!")));
    }
    @Override
    public OrderInfoResponseDTO confirmOrderByStore(Long orderCode) {
        Orders order = ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!"));
        order.setOrderStatus(OrderStatus.prepare);
        order.setConfirmOrder(true);
        ordersRepository.save(order);
        LocalDate deliveryDate = LocalDate.now().plusDays(3);
        Date date = java.sql.Date.valueOf(deliveryDate);
        ReceiveDelivery receiveDelivery = ReceiveDelivery.builder().orders(order).createdDate(new Date()).deliveryDate(date).typeDelivery(TypeDelivery.receive).statusDelivery(StatusDelivery.taking).build();
        receiveDeliveryRepository.save(receiveDelivery);
        order.getReceiveDeliveryList().add(receiveDelivery);
        return OrderMapper.INSTANCE.toOrderInfoResponseDto(order);
    }

    @Override
    public OrderInfoResponseDTO preparedReceiveOrders(Long orderCode) {
        Orders order = ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Khong tim thay don hang"));
        order.setOrderStatus(OrderStatus.pickup);
        order.setConfirmPrepare(true);
        ordersRepository.save(order);
        return OrderMapper.INSTANCE.toOrderInfoResponseDto(order);
    }

    @Override
    public OrderInfoResponseDTO pickUpReceiveOrders(Long orderCode) {
        Orders order = ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Khong tim thay don hang"));
        order.setOrderStatus(OrderStatus.pickup);
        order.setConfirmPickup(true);

        return OrderMapper.INSTANCE.toOrderInfoResponseDto(ordersRepository.save(order));
    }

    @Override
    public OrderInfoResponseDTO updateEvaluate(Long orderCode) {
        Orders order = ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Khong tim thay don hang"));
        order.setIsEvaluate(true);
        ordersRepository.save(order);
        return OrderMapper.INSTANCE.toOrderInfoResponseDto(ordersRepository.save(order));
    }

    @Override
    public OrderInfoResponseDTO confirmOrderPaymentSuccess(Long orderCode) {
        Orders order = ordersRepository.findById(orderCode).orElseThrow(() -> new DataNotFoundException("Khong tim thay don hang"));
        PaymentReceipt paymentReceipt = PaymentReceipt.builder().paymentType(order.getPaymentType()).finalTotal(order.getFinalTotal()).totalAmount(order.getTotalAmount()).totalAmountShip(order.getTotalAmountShip()).totalAmountVoucher(order.getTotalAmountVoucher()).totalAmountDiscount(order.getTotalAmountDiscount()).totalAmountPaid(order.getFinalTotal()).orders(order).createdDate(new Date()).deleted(false).updatedDate(null).deletedDate(null).build();
        ImportExportOrders importExportOrders = ImportExportOrders.builder().typeImportExportOrders(TypeImportExportOrders.exportOrders).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).orders(order).warehouse(Warehouse.builder().id(1L).build()).build();
        order.setOrderStatus(OrderStatus.complete);
        userNotifyService.sendNotifyToUser("Đơn hàng #"+order.getId()+" giao thành công!","Đơn hàng đã giao thành công đến bạn!",order.getId().toString(), TypeNotifycationUser.order,order.getOrderDetailList().get(0).getProductDetail().getImage(),order.getUserAccount().getId());
        ordersRepository.save(order);
        asyncUpdate.updatePaymentWalletStore(order);
        importExportOrdersRepository.save(importExportOrders);
         paymenReceiptRepository.save(paymentReceipt);
        return OrderMapper.INSTANCE.toOrderInfoResponseDto(order);
    }
}
