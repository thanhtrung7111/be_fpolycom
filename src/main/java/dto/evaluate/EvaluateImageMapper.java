package dto.evaluate;

import entity.Evaluate;
import entity.EvaluateImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface EvaluateImageMapper {

    EvaluateImageMapper INSTACNE = Mappers.getMapper(EvaluateImageMapper.class);


    EvaluateImage toEvaluateImage(EvaluateImageRequestDTO requestDTO);

}
