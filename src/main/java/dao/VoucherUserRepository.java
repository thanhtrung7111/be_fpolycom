package dao;

import entity.VoucherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherUserRepository extends JpaRepository<VoucherUser,Long> {


    @Query(value = "select o from VoucherUser o where o.userAccount.userLogin = :userLogin")
    List<VoucherUser> getAllVoucherUser(@Param("userLogin") String userLogin);

}
