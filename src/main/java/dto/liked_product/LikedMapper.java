package dto.liked_product;

import entity.Liked;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface LikedMapper {

    LikedMapper INSTANCE  = Mappers.getMapper(LikedMapper.class);

    @Mapping(target = "productCode",source = "product.id")
    @Mapping(target = "productName",source = "product.name")
    @Mapping(target = "productImage",source = "product.image")
    @Mapping(target = "typeGoodName",source = "product.typeGood.name")
    @Mapping(target = "typeGoodCode",source = "product.typeGood.id")
    LikedProductResponseDTO toLikedProductResponseDto(Liked liked);



    List<LikedProductResponseDTO> toLikedproLikedProductResponseDtoList(List<Liked> likedList);


}
