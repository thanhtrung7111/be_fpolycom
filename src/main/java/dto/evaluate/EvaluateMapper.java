package dto.evaluate;

import entity.Evaluate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {EvaluateImageMapper.class})
public interface EvaluateMapper {

    EvaluateMapper INSTANCE = Mappers.getMapper(EvaluateMapper.class);

    @Mapping(target = "evaluateImageList", source = "imageList")
    Evaluate toEvaluate(UserEvaluateRequestDTO requestDTO);


    @Mapping(target = "imageList", source = "evaluateImageList")
    @Mapping(target = "userCode",source = "userAccount.id")
    @Mapping(target = "username",source = "userAccount.name")
    @Mapping(target = "userImage",source = "userAccount.image")
    @Mapping(target = "dateEvaluate",source = "createdDate")
    @Mapping(target = "productCode",source = "product.id")
    UserEvaluateResponseDTO toUserEvaluateResponseDto(Evaluate evaluate);


    List<UserEvaluateResponseDTO> toUserEvaluateResponseDtoList(List<Evaluate> evaluateList);
}
