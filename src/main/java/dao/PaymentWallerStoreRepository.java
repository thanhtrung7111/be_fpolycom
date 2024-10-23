package dao;

import entity.PaymentWalletStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentWallerStoreRepository extends JpaRepository<PaymentWalletStore,Long> {
}
