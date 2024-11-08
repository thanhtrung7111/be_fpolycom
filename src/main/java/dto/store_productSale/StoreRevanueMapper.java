//package dto.store_productSale;
//
//import entity.Product;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.ReportingPolicy;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
//
//public interface StoreRevanueMapper {
//    StoreRevanueMapper INSTANCE = Mappers.getMapper(StoreRevanueMapper.class);
//
//
//
//    @Mapping(target = "storeCode", source = "storeCode")
//    StoreRevanueResponeDTO toStoreRevanueResponeDto(StoreRevanueResponeDTO revanueResponeDTO);
//
//    //List<ProductsSaleResponeDTO> toProductSalesResponseDTOList(List<Product> products);
//
//
//}
