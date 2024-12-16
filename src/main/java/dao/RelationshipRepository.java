package dao;

import entity.Relationship;
import entity.UserAccount;
import entity.enum_package.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Relation;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship,Long> {


    @Query(value = "select o from Relationship o where o.userAccountPrimary.userLogin = :userLogin and o.friendshipStatus = :status")
    List<Relationship> getAllRelationshipByUserAndStatus(@Param("userLogin") String userLogin,@Param("status") FriendshipStatus status);

    @Query(value = "select o from Relationship o where o.userAccountPrimary.id = :userLogin and o.userAccountSecondary.id = :userCode")
    Optional<Relationship> getRelationshipByUserloginAndUsercode(@Param("userLogin") Long userLogin, @Param("userCode") Long userCode);

    @Query(value = "select o from Relationship o where o.userAccountPrimary = :userAccountPrimary and o.userAccountSecondary = :userAccountSecondary or o.userAccountPrimary = :userAccountSecondary and o.userAccountSecondary = :userAccountPrimary")
    List<Relationship> getAllRelationShipByUserAndUser(@Param("userAccountPrimary")UserAccount userAccount,@Param("userAccountSecondary")UserAccount userAccount2);
}
