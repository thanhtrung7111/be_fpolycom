package service.orders;

import dao.OrdersRepository;
import dao.PaymenReceiptRepository;
import dao.UserAccountRepository;
import dto.order.OrderInfoResponseDTO;
import dto.order.OrderMapper;
import dto.order.OrderResponseDTO;
import dto.order.UserOrderRequestDTO;
import dto.product.ProductMapper;
import entity.Orders;
import entity.PaymentReceipt;
import entity.PaymentType;
import entity.UserAccount;
import entity.enum_package.OrderStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.AsyncUpdate;
import service.auth_user.AuthUserService;

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


    @Override
    public List<OrderResponseDTO> getAllOrderByUser(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        return OrderMapper.INSTANCE.toOrderResponseDtoList(ordersRepository.findAllOrdersByUser(userLoginExtract));
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
                item.getOrderDetailList().forEach(i->i.setOrders(item));
                item.setCreatedDate(new Date());
                item.setUpdatedDate(null);
                item.setDeleted(false);
                item.setDeletedDate(null);
                if(!(item.getVoucherApplyList() == null) && !(item.getVoucherApplyList().size()==0)){
                    item.getVoucherApplyList().forEach(i->{
                        i.setOrders(item);
                        asyncUpdate.updateVoucherUser(userLoginExtract,i.getVoucher().getId());
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
        if(ordersList.isEmpty()){
            throw new DataNotFoundException("Khong co don dat hang nao");
        }
        ordersList.forEach(item->{
            PaymentReceipt paymentReceipt = PaymentReceipt.builder().paymentType(item.getPaymentType()).finalTotal(item.getFinalTotal()).totalAmount(item.getTotalAmount()).totalAmountShip(item.getTotalAmountShip()).totalAmountVoucher(item.getTotalAmountVoucher()).totalAmountPaid(item.getFinalTotal()).orders(item).createdDate(new Date()).deleted(false).updatedDate(null).deletedDate(null).build();
            PaymentReceipt saved = paymenReceiptRepository.save(paymentReceipt);
            item.getPaymentReceiptList().add(saved);
        });

        return OrderMapper.INSTANCE.toOrderInfoResponseDtoList(ordersList);
    }

    @Override
    public OrderInfoResponseDTO getOrderById(Long orderCode) {
        return OrderMapper.INSTANCE.toOrderInfoResponseDto(ordersRepository.findById(orderCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!")));
    }
}
