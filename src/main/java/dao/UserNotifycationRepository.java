package dao;

import entity.NotifycationUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotifycationRepository extends JpaRepository<NotifycationUser,Long> {

    @Query(value = "select o from NotifycationUser o where o.userAccount.userLogin = :userLogin")
    List<NotifycationUser> findAllByUser(@Param("userLogin")String userLogin);

}
