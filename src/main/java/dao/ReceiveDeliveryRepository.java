package dao;

import entity.ReceiveDelivery;
import entity.Shipper;
import entity.enum_package.TypeDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiveDeliveryRepository extends JpaRepository<ReceiveDelivery, Long> {
    @Query("SELECT r FROM ReceiveDelivery r WHERE r.shipper.id = :shipperCode")
    List<ReceiveDelivery> findAllForshipper(@Param("shipperCode") Long shipperCode);


    @Query("SELECT r FROM ReceiveDelivery r WHERE r.typeDelivery = :typeDelivery")
    List<ReceiveDelivery> findAllByTypeDelivery(@Param("typeDelivery")TypeDelivery typeDelivery);


}
