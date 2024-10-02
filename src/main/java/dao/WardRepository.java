package dao;

import entity.Ward;
import org.hibernate.query.criteria.JpaFetchParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward,Long> {
}
