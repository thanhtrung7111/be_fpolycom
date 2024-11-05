package dao;

import entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query(value = "select o from Warehouse o where o.province.id = :ProvinceCode")
    Warehouse findByProvince(@Param("ProvinceCode") String provinceCode);

    @Query(value = "select o from Warehouse o where o.deleted = false")
    List<Warehouse> findByDeleted();

}
