package dto.district;

import dto.province.ProvinceMapper;
import entity.District;
import entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface DistrictMapper {
    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    @Mapping(target = "id",source = "districtCode")
    @Mapping(target = "province.id",source = "provinceCode")
    District toDistrict(AdminDistrictRequestDTO adminDistrictRequestDTO);

    @Mapping(target = "districtCode",source = "id")
    @Mapping(target = "provinceCode",source = "province.id")
    @Mapping(target = "numberOfWards",source = "wardList",qualifiedByName = "numberOfWards")
    AdminDistrictResponseDTO toAdminDistrictResponseDto(District district);


    List<District> toDistrictList(List<AdminDistrictRequestDTO> adminDistrictRequestDTOS);

    List<AdminDistrictResponseDTO> toAdminDistrictResponseDtoList(List<District> districtList);

    @Named("numberOfWards")
    default Integer numberOfWards(List<Ward> wardList){
        return wardList.size();
    }
}
