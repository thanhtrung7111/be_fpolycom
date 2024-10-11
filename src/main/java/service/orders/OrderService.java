package service.orders;

import dto.order.OrderResponseDTO;
import dto.order.UserOrderRequestDTO;

import java.util.List;

public interface OrderService {


    public List<OrderResponseDTO> getAllOrderByUser(String userLogin);

    public List<OrderResponseDTO> postNewOrder(List<UserOrderRequestDTO> userOrderRequestDTO);

}
