package dao;

import entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {


    @Query(value = "select o from ShoppingCart o where o.userAccount.userLogin = :userLogin")
    List<ShoppingCart> findShoppingCartByUserLogin(@Param("userLogin") String userLogin);

    @Query(value = "select o from ShoppingCart o where o.userAccount.userLogin = :userLogin and o.productDetail.id = :productDetailCode")
    Optional<ShoppingCart> findShoppingCartByUserLoginAndPrdcDetail(@Param("userLogin") String userLogin, @Param("productDetailCode") Long productDetalCode);
}
