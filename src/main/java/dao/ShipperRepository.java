package dao;

import entity.Shipper;
import entity.UserAccount;
import entity.enum_package.ShipperStatus;
import entity.enum_package.TypeShipper;
import entity.enum_package.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Optional <Shipper>findByUserLogin(String userLogin);
    Optional <Shipper>findByEmail(String email);

    @Query(value = "select o from Shipper  o where o.id = :shipperCode and o.typeShipper = :typeShipper")
    Shipper findShipperReceiveByID (@Param("shipperCode")Long shipperCpde, @Param("typeShipper") TypeShipper typeShipper);


}
