package dao;

import entity.VoucherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherUserRepository extends JpaRepository<VoucherUser,Long> {


    @Query(value = "select o from VoucherUser o where o.userAccount.userLogin = :userLogin")
    List<VoucherUser> findAllVoucherUser(@Param("userLogin") String userLogin);



    @Query(value = "select o from VoucherUser o where o.userAccount.userLogin = :userLogin and o.voucher.id = :voucherCode")
    Optional<VoucherUser> findVoucherUserByUserLoginAndVoucher(@Param("userLogin") String userLogin, @Param("voucherCode")Long voucherCode);

}
