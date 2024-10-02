package dao;

import entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByUserLogin(String userLogin);


    @Query(value = "select o from UserAccount o where o.tokenRegister.token = :token")
    Optional<UserAccount> findByTokenRegister(@Param("token")String token);
}
