package dao;

import entity.ProductAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttrRepository extends JpaRepository<ProductAttr, Long> {
}
