package dto.product;

import dto.product_attr.ProductAttrMapper;
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

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductDetailMapper.class, ProductAttrMapper.class})
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
    @Mapping(target = "productAttrList", source = "productAttrList")
    @Mapping(target = "storeCode", source = "store.id")
    @Mapping(target = "storeName", source = "store.name")
    ProductInfoResponseDTO toProductInfoResponseDto(Product product);


    List<UserProductResponseDTO> toUserProductResponseDtoList(List<Product> productList);


    @Mapping(target = "typeGood.id", source = "typeGoodCode")
    @Mapping(target = "store.id", source = "storeCode")
    @Mapping(target = "productDetailList", source = "productDetailList")
    @Mapping(target = "productAttrList", source = "productAttrList")
    @Mapping(target = "id", source = "productCode")
    Product toProduct(ProductRequestDTO requestDTO);

    @Named("numberOfLikes")
    default Integer numberOfLikes(List<Liked> likedList) {
        return likedList != null ? likedList.size() : 0;
    }

    @Named("numberOfEvaluates")
    default Integer numberOfEvaluates(List<Evaluate> evaluateList) {
        return evaluateList != null ? evaluateList.size() : 0;
    }

    @Named("pointEvaluate")
    default Double pointEvaluate(List<Evaluate> evaluateList) {
        return evaluateList != null && !evaluateList.isEmpty() ? evaluateList.stream().mapToInt(Evaluate::getQuality).average().orElseThrow(() -> new DataNotFoundException("Khong ton tai du lieu")) : 0;
    }

    @Named("maxPrice")
    default Double maxPrice(List<ProductDetail> productDetailList) {
        return productDetailList != null && !productDetailList.isEmpty() ? productDetailList.stream().mapToDouble(ProductDetail::getPrice).max().orElseThrow(() -> new DataNotFoundException("Khong ton tai du lieu")) : 0;
    }

    @Named("minPrice")
    default Double minPrice(List<ProductDetail> productDetailList) {
        return productDetailList != null && !productDetailList.isEmpty() ? productDetailList.stream().mapToDouble(ProductDetail::getPrice).min().orElseThrow(() -> new DataNotFoundException("Khong ton tai du lieu")) : 0;
    }


}
