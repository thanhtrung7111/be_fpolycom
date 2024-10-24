package dao;

import entity.Store;
import entity.enum_package.StoreStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "select u from Store u where u.userAccount.userLogin = :userLogin")
    Optional<Store> findByUserAccount(@Param("userLogin")String userLogin);

    @Query(value = "select u from Store u where u.id = :storeCode and u.storeStatus = :storeStatus")
    Optional<Store> findStoreByCodeAndStatus(@Param("storeCode")Long storeCode, @Param("storeStatus")StoreStatus storeStatus);

    @Query(value = "select u from Store u where u.id = :storeCode")
    Store findStoreByCode(@Param("storeCode")Long storeCode);

    @Query(value = "select u from Store u where  u.storeStatus = :storeStatus")
    List<Store> findAllStoreByStatus( @Param("storeStatus")StoreStatus storeStatus);

}
