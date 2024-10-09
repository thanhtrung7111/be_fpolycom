package dao;

import entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser,Long> {

    @Query(value = "select o from BankUser o where o.userAccount.userLogin = :userLogin")
    public List<BankUser> findAllBankUserByUserLogin(@Param("userLogin")String userLogin);

    @Query(value = "select o from BankUser o where o.accountNumber = :accountNumber and o.bankBranch.bank.id = :bankCode")
    Optional<BankUser> findBankUserByAccountAndBranch(@Param("accountNumber")String accountNumber,@Param("bankCode")Long bankCode);

}
