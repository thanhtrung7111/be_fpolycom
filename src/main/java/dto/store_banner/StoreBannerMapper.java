package dto.store_banner;

import entity.StoreBanner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreBannerMapper {


    StoreBannerMapper INSTANCE = Mappers.getMapper(StoreBannerMapper.class);

    @Mapping(target = "id",source = "storeBannerCode")
    @Mapping(target = "product.id",source = "productCode")
    @Mapping(target = "store.id",source = "storeCode")
    StoreBanner toStoreBanner(StoreBannerRequest request);


    @Mapping(target = "storeBannerCode",source = "id")
    @Mapping(target = "productCode",source = "product.id")
    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "productName",source = "product.name")
    StoreBannerResponse toStoreBannerResponse(StoreBanner request);


    List<StoreBannerResponse> toStoreBannerResponseList(List<StoreBanner> storeBannerList);
}
