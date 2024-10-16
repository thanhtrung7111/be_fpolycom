package dao;

import entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

    @Query(value = "select o from Bank o where o.deleted = false")
    List<Bank> findAllBankByActive();

}
