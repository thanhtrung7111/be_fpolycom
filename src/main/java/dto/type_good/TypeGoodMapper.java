package dto.type_good;

import entity.Product;
import entity.TypeGood;
import entity.TypeGoodAttr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TypeGoodMapper {
    TypeGoodMapper INSTANCE = Mappers.getMapper(TypeGoodMapper.class);

    @Mapping(target = "id", source = "typeGoodCode")
    TypeGood toTypeGood(TypeGoodRequestDTO dto);

    @Mapping(target = "typeGoodCode", source = "id")
    @Mapping(target = "numberOfProduct",source = "productList", qualifiedByName = "calProductList")
    @Mapping(target = "numberOfAttr",source = "typeGoodAttrList", qualifiedByName = "attrList")
    TypeGoodResponseDTO toTypeGoodResponseDto(TypeGood typeGood);

    List<TypeGoodResponseDTO> toList(List<TypeGood> list);

    @Named("calProductList")
    default  Integer calProductList(List<Product> products){
        if(products == null){
            return 0;
        }
        return  products.size();
    }
    @Named("attrList")
    default Integer attrList(List<TypeGoodAttr> typeGoodAttr){
        if(typeGoodAttr == null){
            return 0;
        }
        return typeGoodAttr.size();
    }
}
