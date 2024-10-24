package dao;

import entity.District;
import entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    @Query(value = "select o from District o where o.deleted = false")
    List<District> findAllNotDeleted();

    @Query(value = "select o from District o where o.province.id = :provinceCode")
    List<District> findDistrictByProvince(@Param("provinceCode")Long provinceCode);
}
