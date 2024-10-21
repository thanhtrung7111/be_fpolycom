package dto.store;

import entity.StoreDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreDocumentMapper {

    StoreDocumentMapper INSTANCE = Mappers.getMapper(StoreDocumentMapper.class);

    @Mapping(target = "id",source = "documentCode")
    StoreDocument toStoreDocument(StoreDocumentRequestDTO requestDTO);

    @Mapping(target = "documentCode",source = "id")
    StoreDocumentResponseDTO toStoreDocumentResponseDto(StoreDocument storeDocument);

    List<StoreDocument> toStoreDocumentList(List<StoreDocumentRequestDTO> storeDocumentRequestDTOList);

    List<StoreDocumentResponseDTO> tStoreDocumentResponseDtoList(List<StoreDocument> storeDocumentRequestDTOList);
}
