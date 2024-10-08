package dto.evaluate;

import entity.Evaluate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring",uses = {EvaluateImageMapper.class})
public interface EvaluateMapper {


        @Mapping(target = "evaluateImageList",source = "imageList")
        Evaluate toEvaluate(UserEvaluateRequestDTO requestDTO);




}
