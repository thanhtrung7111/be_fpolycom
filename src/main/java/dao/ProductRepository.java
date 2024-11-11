package dao;

import entity.Product;
import entity.Store;
import entity.TypeGood;
import entity.enum_package.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select o from Product o where o.productStatus = :status" )
    List<Product> getAllProductByStatus(@Param("status") ProductStatus productStatus);


    @Query(value = "select o from Product o where o.store.id = :storeCode and o.productStatus = :status" )
    List<Product> getAllProductByStoreAndStatus(@Param("storeCode") Long storeCode,@Param("status") ProductStatus productStatus);

    @Query(value = "select o from Product o where o.store.id = :storeCode" )
    List<Product> getAllProductByStore(@Param("storeCode") Long storeCode);


    @Query(value = "select o from Product o where o.id = :productCode and o.productStatus = :productStatus" )
    Optional<Product> findByProductByStatus(@Param("productCode") Long productCode,@Param("productStatus") ProductStatus productStatus);

    @Query(value = "select o.typeGood from Product  o where  o.store = :store")
    List<TypeGood> getTypeGood(Store store);

    @Query(value = "select o from Product  o where o.store = :store and o.typeGood =:typeGood")
    List<Product> getProductsByTypeGood(@Param("store") Store store,@Param("typeGood") TypeGood typeGood);


}
