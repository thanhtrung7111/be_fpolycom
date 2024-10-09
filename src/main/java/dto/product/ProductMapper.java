package dto.product;

import dto.province.AdminProvinceResponseDTO;
import dto.province.BaseProvinceResponseDTO;
import dto.province.ProvinceCreateRequestDTO;
import dto.province.ProvinceMapper;
import entity.District;
import entity.Product;
import entity.Province;
import entity.enum_package.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,componentModel = "spring" )
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapping từ DTO sang Entity
    @Mapping(target = "id", source = "productCode")
    Product toProduct(ProductApproveRequestDTO dto);

    // Mapping từ Entity sang DTO
    @Mapping(target = "productCode", source = "id")
    @Mapping(target = "productStatusCode", source = "productStatus")
    ProductApproveResponeDTO toProductResponseDto(Product entity);

//chuyển đổi danh sách các Product sang danh sách các ProductApproveResponeDTO.
    List<ProductApproveResponeDTO> toProductApproveResponseDTO(List<Product> products);

}
