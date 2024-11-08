package dto.store_productSale;

import entity.Product;
import entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ProductsSaleMapper {
    ProductsSaleMapper INSTANCE = Mappers.getMapper(ProductsSaleMapper.class);


    @Mapping(target = "productCode", source = "product.id")
    @Mapping(target = "storeCode", source = "product.store.id")
    @Mapping(target = "storeName", source = "product.store.name")
    @Mapping(target = "typeGoodCode", source = "product.typeGood.id")
    @Mapping(target = "typeGoodName", source = "product.typeGood.name")
    ProductsSaleResponeDTO toProductsSaleRespone(Product product);

    List<ProductsSaleResponeDTO> toProductSalesResponseDTOList(List<Product> products);


}
