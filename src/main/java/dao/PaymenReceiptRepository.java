package dao;

import entity.PaymentReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymenReceiptRepository extends JpaRepository<PaymentReceipt,Long> {
}
