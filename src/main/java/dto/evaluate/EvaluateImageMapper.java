package dto.evaluate;

import entity.Evaluate;
import entity.EvaluateImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EvaluateImageMapper {

    EvaluateImageMapper INSTACNE = Mappers.getMapper(EvaluateImageMapper.class);


    EvaluateImage toEvaluateImage(EvaluateImageRequestDTO requestDTO);


    List<EvaluateImage> toEvaluateImageList(List<EvaluateImageRequestDTO> evaluateImageRequestDTOList);

    @Mapping(target = "image", source = "image")
    EvaluateImageResponseDTO toEvaluateImageResponseDto(EvaluateImage evaluateImage);

    List<EvaluateImageResponseDTO> toEvaluateImageResponseDtoList(List<EvaluateImage> evaluateImageList);
}
