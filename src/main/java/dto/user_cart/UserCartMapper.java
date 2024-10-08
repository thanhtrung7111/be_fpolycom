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
    UserCartResponseDTO toUserCartResponseDto(ShoppingCart shoppingCart);



    List<UserCartResponseDTO> toUserCartResponseDtoList(List<ShoppingCart> shoppingCartList);
}
