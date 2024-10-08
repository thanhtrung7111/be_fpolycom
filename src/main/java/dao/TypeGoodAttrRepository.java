package dao;

import entity.TypeGoodAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeGoodAttrRepository extends JpaRepository<TypeGoodAttr, Long> {
}
