package dao;



import entity.Orders;
import entity.Ward;
import entity.enum_package.OrderStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {


    @Query(value = "select o from Orders o where o.userAccount.userLogin = :userLogin")
    List<Orders> findAllOrdersByUser(@Param("userLogin")String userLogin);

    @Query(value = "select o from Orders o where o.store.id = :storeCode")
    List<Orders> findAllOrdersByStore(@Param("storeCode")Long storeCode);


    @Query(value = "select o from Orders o where o.orderBillCode = :orderBillCode and o.createdDate = CURRENT_DATE")
    List<Orders> findAllOrdersByOrderBill(@Param("orderBillCode")String orderBillCode);

    @Query(value = "SELECT o FROM Orders o WHERE o.orderStatus = :orderStatus AND o.ward.id = :wardCode")
    List<Orders> findTop5ByOrderStatusAndWard(@Param("orderStatus") OrderStatus orderStatus, @Param("wardCode") Long wardCode, Pageable pageable);

    @Query(value = "select o from Orders o where o.id in :ordersCode")
    List<Orders> findOrdersByOrders(@Param("ordersCode")List<Long> ordersCode);

//    @Query("SELECT new com.be_fpolycom.src.main.java.dto.StoreRevanueResponeDTO(" +
//            "o.store_Code, YEARWEEK(o.created_Date, 1), SUM(od.quantity * p.price)) " +
//            "FROM Order o " +
//            "JOIN o.order_Details od " +
//            "JOIN od.product_Detail p " +
//            "WHERE o.created_Date BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR) AND CURRENT_DATE " +
//            "GROUP BY o.store_Code, YEARWEEK(o.created_Date, 1) " +
//            "ORDER BY YEARWEEK(o.created_Date, 1) DESC")
//    List<StoreRevanueResponeDTO> findWeeklyRevenueByStore();
}
