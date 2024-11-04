package dao;

import entity.PaymentWalletStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentWallerStoreRepository extends JpaRepository<PaymentWalletStore,Long> {


    @Query(value = "select o from PaymentWalletStore o where o.store.id = :storeCode")
    Optional<PaymentWalletStore> findPaymentWalletByStore(@Param("storeCode")Long storeCode);

}
