package dao;

import entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {

    @Query(value = "select o from Province o where o.deleted = false")
    List<Province> getAllNotDeleted();
}
