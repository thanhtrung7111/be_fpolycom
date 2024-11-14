package dao;

import dto.store.*;
import entity.Store;
import entity.TypeGood;
import entity.enum_package.StoreStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "select u from Store u where u.userAccount.userLogin = :userLogin")
    Optional<Store> findByUserAccount(@Param("userLogin")String userLogin);

    @Query(value = "select u from Store u where u.id = :storeCode and u.storeStatus = :storeStatus")
    Optional<Store> findStoreByCodeAndStatus(@Param("storeCode")Long storeCode, @Param("storeStatus")StoreStatus storeStatus);

    @Query(value = "select u from Store u where u.id = :storeCode")
    Store findStoreByCode(@Param("storeCode")Long storeCode);

    @Query(value = "select u from Store u where  u.storeStatus = :storeStatus")
    List<Store> findAllStoreByStatus( @Param("storeStatus")StoreStatus storeStatus);

    @Query(value = "select new dto.store.RevenueByMonthResponseDTO(" +
            "month(o.createdDate), sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name, count(o.id)) " +
            "from Orders o " +
            "where o.store =:store and year(o.createdDate) =:year and o.orderStatus = entity.enum_package.OrderStatus.complete " +
            "group by month(o.createdDate), o.store.name"
    )
    List<RevenueByMonthResponseDTO> findAllRevenueByMonth(@Param("store") Store store, @Param("year") Integer year);

    @Query(value = "select new dto.store.RevenueYearsResponseDTO(" +
            "year(o.createdDate), sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name, count(o.id)) " +
            "from Orders o " +
            "where o.store =:store and o.orderStatus = entity.enum_package.OrderStatus.complete " +
            "group by year(o.createdDate), o.store.name"
    )
    List<RevenueYearsResponseDTO> findAllRevenueYears(@Param("store") Store store);

    @Query(value = "select new dto.store.RevenueByMonthResponseDTO(" +
            "month(o.createdDate), sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name, count(o.id)) " +
            "from Orders o " +
            "where o.store =:store and year(o.createdDate) =:year and month(o.createdDate)=:month and o.orderStatus = entity.enum_package.OrderStatus.complete " +
            " group by month (o.createdDate), o.store.name"
    )
    RevenueByMonthResponseDTO findRevenueByMonthAndYear(@Param("store") Store store,@Param("month") Integer month, @Param("year") Integer year);

    @Query(value = "select new dto.store.RevenueYearsResponseDTO(" +
            "year(o.createdDate), sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name, count(o.id)) " +
            "from Orders o " +
            "where o.store =:store and year(o.createdDate) =:year and o.orderStatus = entity.enum_package.OrderStatus.complete " +
            "group by year(o.createdDate), o.store.name"
    )
    RevenueYearsResponseDTO findRevenueByYear(@Param("store") Store store, @Param("year") Integer year);

    @Query(value = "select new dto.store.RevenueDayResponseDTO(" +
            " o.createdDate, sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name, count(o.id)) " +
            "from Orders o " +
            "where o.store =:store and o.orderStatus = entity.enum_package.OrderStatus.complete " +
            "group by o.createdDate, o.store.name"
    )
    List<RevenueDayResponseDTO> findRevenueByDay(@Param("store") Store store);

    @Query(value = "select new dto.store.Top5ProductBestSellerResponseDTO(" +
            " pd.product.name,  pd.product.typeGood.name, count(od), sum(od.totalAmount  - od.totalDiscount )) " +
            " from OrderDetail od join od.productDetail pd" +
            " where od.orders.store =:store and od.orders.orderStatus = entity.enum_package.OrderStatus.complete " +
            "group by  pd " +
            " order by count(od) desc ")
    List<Top5ProductBestSellerResponseDTO> findTop5ProductBestSeller(@Param("store") Store store, Pageable pageable);

    @Query(value = "select new dto.store.RevenueDayResponseDTO(" +
            "o.createdDate, sum(o.totalAmount - o.totalAmountDiscount - o.totalAmountVoucher), o.store.name, count(o.id)) " +
            "from Orders o " +
            "where o.store =:store and o.createdDate =:date and o.orderStatus = entity.enum_package.OrderStatus.complete " +
            " group by o.createdDate, o.store.name"
    )
    RevenueDayResponseDTO findRevenueByMonthAndYearAndDay(@Param("store") Store store,@Param("date") Date date);

    @Query(value = "select new dto.store.Top5ProductBestSellerResponseDTO(" +
            "pd.product.name,  pd.product.typeGood.name, count(od), sum(od.totalAmount  - od.totalDiscount )) " +
            " from OrderDetail od join od.productDetail pd" +
            " where od.orders.store =:store and pd.product.typeGood =:typeGood " +
            "group by  pd.product.typeGood.id, pd.product.id " )
    List<Top5ProductBestSellerResponseDTO> findProductByTypeGood(@Param("store") Store store,@Param("typeGood") TypeGood typeGood);

    @Query(value = "select new dto.store.NumberOfProductByTypeResponseDTO(" +
            " count(p),p.typeGood.name ) " +
            " from Product p " +
            " where p.store = :store " +
            "group by p.typeGood.id")
    List<NumberOfProductByTypeResponseDTO> NumberOfProductByType(@Param("store") Store store);


}
