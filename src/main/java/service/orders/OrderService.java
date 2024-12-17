package service.orders;

import dto.order.OrderInfoResponseDTO;
import dto.order.OrderResponseDTO;
import dto.order.OrderShipperResponseDTO;
import dto.order.UserOrderRequestDTO;
import entity.Orders;

import java.util.List;

public interface OrderService {

    public List<OrderResponseDTO> getAllOrderByUser(String userLogin);

    public List<OrderResponseDTO> getAllOrder();

    public OrderShipperResponseDTO getShipperOrderById(Long orderCode);
    public List<OrderInfoResponseDTO> postNewOrder(List<UserOrderRequestDTO> userOrderRequestDTO);

    public List<OrderInfoResponseDTO> confirmOrder(String orderBillCode);

    public List<OrderResponseDTO> getAllOrderByStore(Long storeCode);

    public OrderInfoResponseDTO getOrderById(Long orderCode);
    public OrderInfoResponseDTO confirmOrderByStore(Long orderCode);

    public OrderInfoResponseDTO preparedReceiveOrders(Long orderCode);

    public OrderInfoResponseDTO pickUpReceiveOrders(Long orderCode);

    public OrderInfoResponseDTO updateEvaluate(Long orderCode);

    public OrderInfoResponseDTO confirmOrderPaymentSuccess(Long orderCode);

//
}
