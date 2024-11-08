package dao;


import entity.Product;
import entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    @Query(value = "SELECT p.*, SUM(od.quantity) FROM Product p " +
            "JOIN Product_Detail pd ON pd.product_code = p.product_code " +
            "JOIN Order_Detail od ON od.product_detail_code = pd.product_detail_code " +
            "JOIN `orders` o ON o.order_code = od.order_code " +
            "WHERE o.store_code = :storeCode AND p.name = :typeGoodName " +
            "GROUP BY p.product_code ORDER BY SUM(od.quantity) DESC LIMIT 5", nativeQuery = true)
    List<Product> findTop5SoldProductsByStoreAndCategory(Long storeCode, String typeGoodName);


}
