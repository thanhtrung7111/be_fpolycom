package dao;

import entity.MessageStoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageStoreUserRepository extends JpaRepository<MessageStoreUser,Long> {

    @Query(value = "select o from MessageStoreUser o where o.groupMessageStore.id = :groupCode")
    List<MessageStoreUser> findAllByGroundMess(@Param("groupCode")Long groupCode);

}
