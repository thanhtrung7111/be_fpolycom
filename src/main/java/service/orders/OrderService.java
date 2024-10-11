package service.orders;

import dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {


    public List<OrderResponseDTO> getAllOrderByUser(String userLogin);

}
