package service.orders;

import dto.order.OrderInfoResponseDTO;
import dto.order.OrderResponseDTO;
import dto.order.UserOrderRequestDTO;

import java.util.List;

public interface OrderService {


    public List<OrderResponseDTO> getAllOrderByUser(String userLogin);

    public List<OrderInfoResponseDTO> postNewOrder(List<UserOrderRequestDTO> userOrderRequestDTO);

    public List<OrderInfoResponseDTO> confirmOrder(String orderBillCode);

}
