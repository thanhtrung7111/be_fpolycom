package dao;

import entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {



    @Query(value = "select o from Voucher o where o.store.id = :storeCode")
    List<Voucher> findAllVoucherByStore(@Param("storeCode")Long storeCode);
}
