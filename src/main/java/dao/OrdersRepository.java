package dao;


import entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {


    @Query(value = "select o from Orders o where o.userAccount.userLogin = :userLogin")
    List<Orders> findAllOrdersByUser(@Param("userLogin")String userLogin);

}
