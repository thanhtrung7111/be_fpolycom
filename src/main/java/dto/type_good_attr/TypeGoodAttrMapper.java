package dto.type_good_attr;

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
public interface TypeGoodAttrMapper {
    TypeGoodAttrMapper INSTANCE = Mappers.getMapper(TypeGoodAttrMapper.class);

    @Mapping(target = "id", source = "typeGoodAttrCode")
    @Mapping(target = "typeGood.id", source = "typeGoodCode")
    TypeGoodAttr toTypeGoodAttr(TypeGoodAttrRequestDTO dto);

    @Mapping(target = "typeGoodAttrCode", source = "id")
    @Mapping(target = "typeGoodCode", source = "typeGood.id")
    @Mapping(target = "numberOfProductAttr", source = "productAttrList", qualifiedByName = "numberOfProducts")
    TypeGoodAttrResponseDTO toTypeGoodAttrResponseDTO(TypeGoodAttr typeGoodAttr);

    List<TypeGoodAttrResponseDTO> toList(List<TypeGoodAttr> typeGoodAttrList);

    @Named("numberOfProducts")
    default Integer numberOfProducts(List<ProductAttr> products) {
        if (products == null) {
            return 0;
        }
        return products.size();
    }
}

