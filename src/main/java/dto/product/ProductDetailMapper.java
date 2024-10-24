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
//    @Mapping(target = "percentDecrease",source = "discount.percentDecrease")
    ProductDetailResponseDTO toProductDetailResponseDTO(ProductDetail productDetail);


    @Mapping(target = "id",source = "productDetailCode")
    @Mapping(target = "discount.id",source = "discountCode")
    ProductDetail toProductDetail(ProductDetailRequestDTO productDetailRequestDTO);

    List<ProductDetailResponseDTO> toProductDetailResponseDtoList(List<ProductDetail> productDetailList);

    List<ProductDetail> toProductDetailList(List<ProductDetailRequestDTO> productDetailRequestDTOList);
}
