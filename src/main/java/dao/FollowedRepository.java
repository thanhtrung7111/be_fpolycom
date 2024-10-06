package dao;

import entity.Followed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowedRepository extends JpaRepository<Followed,Long> {


    @Query(value = "select o from  Followed o where o.userAccount.userLogin = :userLogin")
    List<Followed> findAllByUserLogin(@Param("userLogin") String userLogin);

}
