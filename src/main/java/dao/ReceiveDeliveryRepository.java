package dao;

import entity.ReceiveDelivery;
import entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiveDeliveryRepository extends JpaRepository<ReceiveDelivery, Long> {
    @Query(value = "select o from ReceiveDelivery o where o.shipper.id =:shipperCode")
    public List <ReceiveDelivery> findAllForshipper(@Param("shipperCode")Long shipperCode);


}
