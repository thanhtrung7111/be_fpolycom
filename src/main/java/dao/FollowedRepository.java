package dao;

import entity.Followed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowedRepository extends JpaRepository<Followed,Long> {


    @Query(value = "select o from  Followed o where o.userAccount.userLogin = :userLogin")
    List<Followed> findAllByUserLogin(@Param("userLogin") String userLogin);


    @Query(value = "select o from Followed o where o.userAccount.userLogin = :userLogin and o.store.id = :storeCode")
    Optional<Followed> findAllByUserLoginAndStore(@Param("userLogin") String userLogin, @Param("storeCode")Long storeCode);
}
