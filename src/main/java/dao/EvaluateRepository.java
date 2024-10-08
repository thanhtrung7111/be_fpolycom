package dao;

import entity.Evaluate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateRepository  extends JpaRepository<Evaluate,Long> {

    @Query(value = "select o from Evaluate o LEFT JOIN FETCH o.evaluateImageList where o.product.id = :productCode")
    List<Evaluate> findEvaluateByProduct (@Param("productCode") Long productCode);

}
