package dto.product;

import entity.Evaluate;
import entity.Liked;
import entity.Product;
import entity.ProductDetail;
import exeception_handler.DataNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,uses = {ProductDetailMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    @Mapping(target = "typeGoodName", source = "typeGood.name")
    @Mapping(target = "productCode", source = "id")
    @Mapping(target = "typeGoodCode", source = "typeGood.id")
    @Mapping(target = "status", source = "productStatus")
    @Mapping(target = "numberOfLikes", source = "likedList", qualifiedByName = "numberOfLikes")
    @Mapping(target = "numberOfEvaluates", source = "evaluateList", qualifiedByName = "numberOfEvaluates")
    @Mapping(target = "pointEvaluate", source = "evaluateList", qualifiedByName = "pointEvaluate")
    @Mapping(target = "maxPrice", source = "productDetailList", qualifiedByName = "maxPrice")
    @Mapping(target = "minPrice", source = "productDetailList", qualifiedByName = "minPrice")
    UserProductResponseDTO toUserProductResponseDto(Product product);

    @Mapping(target = "typeGoodName", source = "typeGood.name")
    @Mapping(target = "productCode", source = "id")
    @Mapping(target = "productDetailList", source = "productDetailList")
    @Mapping(target = "typeGoodCode", source = "typeGood.id")
    @Mapping(target = "status", source = "productStatus")
    @Mapping(target = "numberOfLikes", source = "likedList", qualifiedByName = "numberOfLikes")
    @Mapping(target = "numberOfEvaluates", source = "evaluateList", qualifiedByName = "numberOfEvaluates")
    @Mapping(target = "pointEvaluate", source = "evaluateList", qualifiedByName = "pointEvaluate")
    @Mapping(target = "maxPrice", source = "productDetailList", qualifiedByName = "maxPrice")
    @Mapping(target = "minPrice", source = "productDetailList", qualifiedByName = "minPrice")
    ProductInfoResponseDTO toProductInfoResponseDto(Product product);



    List<UserProductResponseDTO> toUserProductResponseDtoList(List<Product> productList);


    @Named("numberOfLikes")
    default Integer numberOfLikes(List<Liked> likedList) {
        return likedList.size();
    }

    @Named("numberOfEvaluates")
    default Integer numberOfEvaluates(List<Evaluate> evaluateList) {
        return evaluateList.size();
    }

    @Named("pointEvaluate")
    default Double pointEvaluate(List<Evaluate> evaluateList) {
        return !evaluateList.isEmpty() ? evaluateList.stream().mapToInt(Evaluate::getQuality).average().orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu")) : 0;
    }

    @Named("maxPrice")
    default Double maxPrice(List<ProductDetail> productDetailList) {
        return !productDetailList.isEmpty() ? productDetailList.stream().mapToDouble(ProductDetail::getPrice).max().orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu")) : 0;
    }

    @Named("minPrice")
    default Double minPrice(List<ProductDetail> productDetailList) {
        return !productDetailList.isEmpty() ? productDetailList.stream().mapToDouble(ProductDetail::getPrice).min().orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu")) : 0;
    }


}
