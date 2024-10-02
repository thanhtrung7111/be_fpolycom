package dto.province;

import entity.District;
import entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,componentModel = "spring" )
public interface ProvinceMapper {
    ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);

    @Mapping(target = "id",source = "provinceCode")
    Province toEnity(ProvinceCreateRequestDTO dto);

    @Mapping(target = "provinceCode",source = "id")
    @Mapping(target = "numberOfDistricts",source = "districtList",qualifiedByName = "calculateNumberOfDistricts")
    AdminProvinceResponseDTO toAdminProvinceResponseDto(Province entity);

    @Mapping(target = "provinceCode",source = "id")
    BaseProvinceResponseDTO toBaseProvinceResponseDto(Province entity);

    List<AdminProvinceResponseDTO> toAdminProvinceResponseDtos(List<Province> list);

    List<BaseProvinceResponseDTO> toBaseProvinceResponseDtos(List<Province> list);

    @Named("calculateNumberOfDistricts")
    default Integer calculateNumberOfDistricts(List<District> districts) {
        if(districts == null){
            return 0;
        }
        return  districts.size();
    }
}
