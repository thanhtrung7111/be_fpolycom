package dao;

import entity.StoreTransaction;
import entity.enum_package.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreTransactionRepository extends JpaRepository<StoreTransaction, Long> {
    @Query(value = "select o from StoreTransaction o where o.transactionStatus = :status")
    List<StoreTransaction> findPendingTransactions(@Param("status") TransactionStatus status);
}

