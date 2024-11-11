package dao;

import entity.OrderDetail;
import entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

}
