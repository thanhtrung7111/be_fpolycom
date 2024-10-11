package service.orders;

import dao.OrdersRepository;
import dao.UserAccountRepository;
import dto.order.OrderMapper;
import dto.order.OrderResponseDTO;
import dto.order.UserOrderRequestDTO;
import entity.Orders;
import entity.UserAccount;
import entity.enum_package.OrderStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;


    @Override
    public List<OrderResponseDTO> getAllOrderByUser(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        return OrderMapper.INSTANCE.toOrderResponseDtoList(ordersRepository.findAllOrdersByUser(userLoginExtract));
    }

    @Override
    public List<OrderResponseDTO> postNewOrder(List<UserOrderRequestDTO> userOrderRequestDTO) {
        if (!userOrderRequestDTO.isEmpty()) {
            if (!authUserService.isValidUserLogin(userOrderRequestDTO.getFirst().getUserLogin())) {
                throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
            }

            String userLoginExtract = authUserService.extractUserlogin(userOrderRequestDTO.getFirst().getUserLogin());
            UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Nguoi dung khong ton tai!"));
            List<Orders> listConvert = OrderMapper.INSTANCE.toOrdersList(userOrderRequestDTO);
            listConvert.forEach(item -> {
                item.setUserAccount(userAccount);
                item.setOrderStatus(OrderStatus.pending);
                item.getOrderDetailList().forEach(i->i.setOrders(item));
            });
            ordersRepository.saveAll(listConvert);
            return OrderMapper.INSTANCE.toOrderResponseDtoList(listConvert);
        }
        throw new DataNotFoundException("Du lieu khogn ton tai!");

    }
}
