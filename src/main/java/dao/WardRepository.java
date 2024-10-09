package dao;

import entity.Ward;
import org.hibernate.query.criteria.JpaFetchParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward,Long> {
    @Query(value = "select o from Ward o where o.deleted = false")
    List<Ward> findAllNotDeleted();
}
