package dto.admin_product;


import entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdminProductMapper {
    AdminProductMapper INSTANCE = Mappers.getMapper(AdminProductMapper.class);

    @Mapping(target = "typeGoodName", source = "typeGood.name")
    @Mapping(target = "productCode", source = "id")
    @Mapping(target = "typeGoodCode", source = "typeGood.id")
    @Mapping(target = "status", source = "productStatus")
    AdminProductResponseDTO toAdminProductResponseDTO(Product product);

    List<AdminProductResponseDTO> toList (List<Product> products);
}
