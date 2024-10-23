package dao;

import entity.TypeGoodAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeGoodAttrRepository extends JpaRepository<TypeGoodAttr, Long> {


    @Query(value = "select o from TypeGoodAttr o where o.typeGood.id = :typeGoodCode")
    List<TypeGoodAttr> findAllByTypeGood(@Param("typeGoodCode")Long typeGoodCode);
}
