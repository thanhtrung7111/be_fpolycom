package dto.product;

import entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDetailMapper {

    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);


    @Mapping(target = "productDetailCode",source = "id")
    @Mapping(target = "discountCode",source = "discount.id")
    @Mapping(target = "percentDecrease",source = "discount.percentDecrease")
    ProductDetailResponseDTO toProductDetailResponseDTO(ProductDetail productDetail);

    List<ProductDetailResponseDTO> toProductDetailResponseDtoList(List<ProductDetail> productDetailList);
}
