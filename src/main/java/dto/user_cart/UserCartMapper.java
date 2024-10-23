package dto.user_cart;

import entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserCartMapper {

    UserCartMapper INSTANCE  = Mappers.getMapper(UserCartMapper.class);

    @Mapping(target = "productName",source = "productDetail.product.name")
    @Mapping(target = "productDetailCode",source = "productDetail.id")
    @Mapping(target = "detailName",source = "productDetail.name")
    @Mapping(target = "quantity",source = "quantity")
    @Mapping(target = "price",source = "productDetail.price")
    @Mapping(target = "discountCode",source = "productDetail.discount.id")
    @Mapping(target = "percentDecrease",source = "productDetail.discount.percentDecrease")
    @Mapping(target = "image",source = "productDetail.image")
    @Mapping(target = "productCode",source = "productDetail.product.id")
    @Mapping(target = "storeCode",source = "productDetail.product.store.id")
    @Mapping(target = "storeName",source = "productDetail.product.store.name")
    @Mapping(target = "provinceCode",source = "productDetail.product.store.province.id")
    UserCartResponseDTO toUserCartResponseDto(ShoppingCart shoppingCart);



    List<UserCartResponseDTO> toUserCartResponseDtoList(List<ShoppingCart> shoppingCartList);
}
