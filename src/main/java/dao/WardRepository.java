package dao;

import entity.Ward;
import org.hibernate.query.criteria.JpaFetchParent;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaFetchParent<Ward,Long> {
}
