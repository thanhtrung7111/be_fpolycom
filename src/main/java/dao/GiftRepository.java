package dao;

import entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Long> {


    @Query(value = "select o from  Gift o where o.orders.userAccount.userLogin = :userLogin")
    List<Gift> findAllGiftByUserLogin(@Param("userLogin")String userLogin);

}
