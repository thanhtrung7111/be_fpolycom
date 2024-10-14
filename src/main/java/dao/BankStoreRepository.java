package dao;

import entity.BankStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankStoreRepository extends JpaRepository<BankStore, Long> {
}
