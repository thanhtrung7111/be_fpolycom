package dao;

import entity.StoreTransactionReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreTransactionReceiptRepository extends JpaRepository<StoreTransactionReceipt, Long> {
}
