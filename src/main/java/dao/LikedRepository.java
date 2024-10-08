package dao;

import entity.Liked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikedRepository extends JpaRepository<Liked,Long> {


    @Query(value = "select o from Liked o where o.userAccount.userLogin = :userLogin")
    List<Liked> findAllByUserLogin(@Param("userLogin") String userLogin);

    @Query(value = "select o from Liked o where o.userAccount.userLogin = :userLogin and o.product.id = :productCode")
    Optional<Liked> findLikedByUserLoginAndProduct(@Param("userLogin") String userLogin,@Param("productCode") Long productCode);
}
