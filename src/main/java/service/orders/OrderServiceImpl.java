package service.orders;

import dao.OrdersRepository;
import dto.order.OrderMapper;
import dto.order.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrdersRepository ordersRepository;

   @Autowired
    AuthUserService authUserService;

    @Override
    public List<OrderResponseDTO> getAllOrderByUser(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        return OrderMapper.INSTANCE.toOrderResponseDtoList(ordersRepository.findAllOrdersByUser(userLoginExtract));
    }
}
