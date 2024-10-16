package dto.ward;

import entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface WardMapper {
    WardMapper INSTANCE = Mappers.getMapper(WardMapper.class);

    @Mapping(target = "id" , source = "wardCode")
    @Mapping(target = "district.id" , source = "districtCode")
    Ward toWard(WardCreateRequestDTO dto);

    @Mapping(target = "wardCode", source = "ward.id")
    @Mapping(target = "districtCode", source = "district.id")
    AdminWardResponseDTO adminWardToAdminWardDTO(Ward ward);

    @Mapping(target = "wardCode", source = "ward.id")
    BaseWardResponseDTO toBaseWardResponseDto(Ward ward);

    List<Ward> toWardList (List<AdminWardResponseDTO> adminWardResponseDTOS);



    List<AdminWardResponseDTO> toAdminWardList (List<Ward> wardList);

    List<BaseWardResponseDTO> toBaseWardResponseDtoList (List<Ward> wardList);
}
