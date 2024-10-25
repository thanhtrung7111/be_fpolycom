package dao;

import entity.StoreBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreBannerRepository extends JpaRepository<StoreBanner,Long> {


    @Query(value = "select o from StoreBanner o where o.store.id = :storeCode")
    List<StoreBanner> findAllStoreBannerByStore(@Param("storeCode") Long storeCode);

    @Query(value = "select o from StoreBanner o where o.store.id = :storeCode and o.status = :status")
    List<StoreBanner> findAllStoreBannerByStoreAndStatus(@Param("storeCode") Long storeCode,@Param("status")boolean status);
}
