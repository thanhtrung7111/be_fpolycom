package dto.store;

import dao.StoreRepository;
import entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,uses = {StoreDocumentMapper.class})
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);


    @Mapping(target = "id", source = "storeRegisterCode")
    @Mapping(target = "province.id", source = "provinceCode")
    @Mapping(target = "district.id", source = "districtCode")
    @Mapping(target = "ward.id", source = "wardCode")
    @Mapping(target = "storeDocumentList",source = "documentList")
    Store toStore(StoreRegisterRequestDTO requestDTO);

    @Mapping(target = "storeRegisterCode", source = "id")
    @Mapping(target = "provinceCode", source = "province.id")
    @Mapping(target = "provinceName", source = "province.name")
    @Mapping(target = "districtCode", source = "district.id")
    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "wardCode", source = "ward.id")
    @Mapping(target = "wardName", source = "ward.name")
    @Mapping(target = "documentList",source = "storeDocumentList")
    @Mapping(target = "status",source = "storeStatus")
    @Mapping(target = "userRegister",source = "store.userAccount.name")
    StoreRegisterResponseDTO toStoreRegisterResponseDto(Store store);

    @Mapping(target = "provinceName", source = "province.name")
    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "wardName", source = "ward.name")
    @Mapping(target = "storeCode",source = "id")
    @Mapping(target = "status",source = "storeStatus")
    @Mapping(target = "userRegister",source = "store.userAccount.name")
    @Mapping(target = "numberOfFollowed",source = "followedList",qualifiedByName = "numberOfFollowed")
    @Mapping(target = "numberOfLiked",source = "productList",qualifiedByName = "numberOfLiked")
    UserStoreDetailResponseDTO toUserStoreDetailResponseDto(Store store);

    @Mapping(target = "provinceName", source = "province.name")
    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "wardName", source = "ward.name")
    @Mapping(target = "status",source = "storeStatus")
    @Mapping(target = "storeCode",source = "id")
    @Mapping(target = "userRegister",source = "store.userAccount.name")
    @Mapping(target = "numberOfFollowed",source = "followedList",qualifiedByName = "numberOfFollowed")
    @Mapping(target = "numberOfLiked",source = "productList",qualifiedByName = "numberOfLiked")
    StoreResponseDTO toUserStoreResponseDto(Store store);

//    @Mapping(target = "numberOfProduct", source ="productList", qualifiedByName = "numberOfProduct")
//    @Mapping(target = "revenueByMonth", source ="ordersList",qualifiedByName = "revenueByMonth")
//    @Mapping(target = "revenueByYear", source ="ordersList")
//    @Mapping(target = "numberOfProductByType",source = "productList",qualifiedByName = "numberOfProductByType")
//    @Mapping(target = "Top5BestSeller",source = "productList")
//    @Mapping(target = "Top5BestSellerByType",source = "productList")
//    DashboardStoreResponseDTO dashboardStoreResponseDto(Store store);

    List<StoreRegisterResponseDTO> toStoreRegisterResponseDtoList(List<Store> storeList);


    List<StoreResponseDTO> toUserStoreResponseDtoList(List<Store> storeList);

    List<Store> toStoreList(List<StoreRegisterRequestDTO> storeRegisterRequestDTOList);


    @Named("numberOfFollowed")
    default Integer numberOfFollowed(List<Followed> followeds){
        return  followeds.size();
    }


    @Named("numberOfLiked")
    default Integer numberOfLiked(List<Product> productList){
        return  productList.stream().mapToInt(item->item.getLikedList().size()).sum();
    }

//    @Named("numberOfProduct")
//    default Integer numberOfProduct(List<Product> productList){
//        return  productList.size();
//    }
//    @Named("revenueByMonth")
//    default Double revenueByMonth(List<Orders> ordersList){
//        return ordersList.stream()
//                .filter(orders -> {
//                    Date ordersDate = orders.getCreatedDate();
//                    return ordersDate.getMonth() == new Date().getMonth();
//                })
//                .flatMap(order -> order.getOrderDetailList().stream())
//                .mapToDouble(OrderDetail::getFinalTotal)
//                .sum();
//    }
//    @Named("numberOfProductByType")
//    default Integer numberOfProductByType(List<Product> productList){
//        return  productList.size();
//    }


}
