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


    @Query(value = "SELECT m\n" +
            "FROM GroupMessageStore g\n" +
            "JOIN MessageStoreUser m ON m.groupMessageStore.id = g.id\n" +
            "WHERE m.timeSend = (\n" +
            "    SELECT MAX(m2.timeSend)\n" +
            "    FROM MessageStoreUser m2\n" +
            "    WHERE m2.groupMessageStore.store.id = :storeCode\n" +
            ")")
    List<MessageStoreUser> findAllMessageLatestByStore(@Param("storeCode")Long storeCode);

    @Query(value = "SELECT m\n" +
            "FROM GroupMessageStore g\n" +
            "JOIN MessageStoreUser m ON m.groupMessageStore.id = g.id\n" +
            "WHERE m.timeSend = (\n" +
            "    SELECT MAX(m2.timeSend)\n" +
            "    FROM MessageStoreUser m2\n" +
            "    WHERE m2.groupMessageStore.userAccount.id = :userCode\n" +
            ")")
    List<MessageStoreUser> findAllMessageLatestByUser(@Param("userCode")Long userCode);


}
