package dao;

import entity.UserAccount;
import entity.enum_package.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByUserLogin(String userLogin);

    @Query(value = "select o from UserAccount o where o.userLogin = :userLogin and o.userStatus = :userStatus")
    Optional<UserAccount> findByUserLoginAndStatus(@Param("userLogin") String userLogin,@Param("userStatus") UserStatus userStatus);

    @Query(value = "select o from UserAccount o where o.tokenRegister.token = :token")
    Optional<UserAccount> findByTokenRegister(@Param("token")String token);


    Optional<UserAccount> findByEmail(String token);


    @Query(value = "select o from UserAccount o where o.passwordRecover.tokenRecover = :token")
    Optional<UserAccount> findByTokenPasswordRecover(@Param("token")String token);
}
