package dao;

import entity.Shipper;
import entity.UserAccount;
import entity.enum_package.ShipperStatus;
import entity.enum_package.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Optional <Shipper>findByUserLogin(String userLogin);


}
