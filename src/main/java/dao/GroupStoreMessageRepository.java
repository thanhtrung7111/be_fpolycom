package dao;

import entity.GroupMessageStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupStoreMessageRepository extends JpaRepository<GroupMessageStore,Long> {


    @Query(value = "select o from GroupMessageStore o where o.store.id = :storeCode")
    List<GroupMessageStore> findAllByStoreCode(@Param("storeCode")Long storeCode);

    @Query(value = "select o from GroupMessageStore o where o.userAccount.id = :userCode")
    List<GroupMessageStore> findAllByUserCode(@Param("userCode")Long userCode);
}
