package dto.product_attr;

import dto.type_good_attr.TypeGoodAttrRequestDTO;
import dto.type_good_attr.TypeGoodAttrResponseDTO;
import entity.Product;
import entity.ProductAttr;
import entity.TypeGoodAttr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductAttrMapper {
    ProductAttrMapper INSTANCE = Mappers.getMapper(ProductAttrMapper.class);


    @Mapping(target = "id", source = "productAttrCode")
    @Mapping(target = "typeGoodAttr.id", source = "typeGoodAttrCode")
    @Mapping(target = "product.id", source = "productCode")
    ProductAttr toProductAttr(ProductAttrRequestDTO dto);

    @Mapping(target = "productAttrCode", source = "id")
    @Mapping(target = "typeGoodAttrCode", source = "typeGoodAttr.id")
    @Mapping(target = "productCode",source = "product.id")
    ProductAttrResponseDTO toProductAttrResponseDTO(ProductAttr productAttr);

    List<ProductAttrResponseDTO> toList(List<ProductAttr> productAttrList);


}
