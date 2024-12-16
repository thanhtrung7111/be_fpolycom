package dto.key_search;

import entity.KeySearch;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KeySearchMapper {

    public KeySearchMapper INSTANCE = Mappers.getMapper(KeySearchMapper.class);


    public KeySearchResponseDTO toKeySearchResponseDto (KeySearch keySearch);


    List<KeySearchResponseDTO> toKeySearchResponseDtoList(List<KeySearch> keySearches);


}
