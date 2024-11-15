package dao;

import dto.user_account.*;
import entity.Store;
import entity.UserAccount;
import entity.enum_package.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByUserLogin(String userLogin);

    @Query(value = "select o from UserAccount o where o.userLogin = :userLogin and o.userStatus = :userStatus")
    Optional<UserAccount> findByUserLoginAndStatus(@Param("userLogin") String userLogin,@Param("userStatus") UserStatus userStatus);

    @Query(value = "select o from UserAccount o where o.tokenRegister.token = :token")
    Optional<UserAccount> findByTokenRegister(@Param("token")String token);


    Optional<UserAccount> findByEmail(String token);


    @Query(value = "select o from UserAccount o where o.passwordRecover.tokenRecover = :token")
    Optional<UserAccount> findByTokenPasswordRecover(@Param("token")String token);

    @Query (value ="select new dto.user_account.UserCreateByMonthResponseDTO(" +
            " count(u.id),year(u.createdDate),month (u.createdDate)) " +
            " from UserAccount u " +
            " where year(u.createdDate) =:year " +
            " group by year(u.createdDate),month (u.createdDate)" )
    List<UserCreateByMonthResponseDTO> findUserCreateByYear(@Param("year") Integer year);

    @Query (value ="select new dto.user_account.UserCreateDayByDayResponseDTO(" +
            " u.createdDate ,count(u.id )) " +
            " from UserAccount u " +
            " where u.createdDate between :startDate and :endDate" +
            " group by u.createdDate")
    List<UserCreateDayByDayResponseDTO> findUserCreateDayByDay(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query (value ="select new dto.user_account.UserCreateByYearResponseDTO(" +
            " count(u.id),year(u.createdDate)) " +
            " from UserAccount u " +
            " where year(u.createdDate) between :startYear and :endYear " +
            " group by year(u.createdDate) " )
    List<UserCreateByYearResponseDTO> findUserCreateBetweenYear(@Param("startYear") Integer startYear,@Param("endYear")Integer endYear);

    @Query (value ="select new dto.user_account.NumberOfOrdersByMonthResponseDTO(" +
            " count(o),month(o.createdDate),year (o.createdDate)) " +
            " from Orders o " +
            " where year(o.createdDate) =:year " +
            " group by year(o.createdDate),month (o.createdDate)" )
    List<NumberOfOrdersByMonthResponseDTO> findOrdersByYear(@Param("year") Integer year);

    @Query (value ="select new dto.user_account.NumberOfOrdersByYearResponseDTO(" +
            " count(u.id),year(u.createdDate)) " +
            " from Orders u " +
            " where year(u.createdDate) between :startYear and :endYear " +
            " group by year(u.createdDate) " )
    List<NumberOfOrdersByYearResponseDTO> findOrdersBetweenYear(@Param("startYear") Integer startYear,@Param("endYear")Integer endYear);

    @Query (value ="select new dto.user_account.NumberOfOrdersDayByDayResponseDTO(" +
            " count(u.id ), (u.createdDate) )" +
            " from Orders u " +
            " where u.createdDate between :startDate and :endDate" +
            " group by u.createdDate")
    List<NumberOfOrdersDayByDayResponseDTO> findOrdersDayByDay(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select  new dto.user_account.RevenueByStoreInDayResponseDTO(" +
            " o.createdDate, sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name,count(o))" +
            " from Orders o" +
            " where o.createdDate = :date" +
            " group by o.createdDate,o.store")
    List<RevenueByStoreInDayResponseDTO> findRevenueByStoreInDay(@Param("date") Date date);

    @Query(value = "select  new dto.user_account.RevenueByStoreInMonthResponseDTO(" +
            " month(o.createdDate),year(o.createdDate), sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name,count(o))" +
            " from Orders o" +
            " where month(o.createdDate) =:month and year(o.createdDate) =:year " +
            " group by month(o.createdDate),year(o.createdDate),o.store.name")
    List<RevenueByStoreInMonthResponseDTO> findRevenueByStoreInMonth(@Param("month") Integer month,@Param("year")Integer year);

    @Query(value = "select  new dto.user_account.RevenueByStoreInYearResponseDTO(" +
            " year(o.createdDate), sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name,count(o))" +
            " from Orders o" +
            " where year(o.createdDate) = :year" +
            " group by year(o.createdDate),o.store.name")
    List<RevenueByStoreInYearResponseDTO> findRevenueByStoreInYear( @Param("year") Integer year);


}