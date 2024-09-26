package dto.province;

import entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,componentModel = "spring" )
public interface ProvinceMapper {
    ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);

    @Mapping(target = "id",source = "provinceCode")
    Province toEnity(ProvinceCreateRequestDTO dto);

    @Mapping(target = "provinceCode",source = "id")
    ProvinceResponseDTO toDTO(Province entity);

    List<ProvinceResponseDTO> toDTOList(List<Province> list);
}
