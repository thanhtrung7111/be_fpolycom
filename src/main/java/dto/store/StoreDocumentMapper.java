package dto.store;

import entity.StoreDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreDocumentMapper {

    StoreDocumentMapper INSTANCE = Mappers.getMapper(StoreDocumentMapper.class);

    StoreDocument toStoreDocument(StoreDocumentRequestDTO requestDTO);

    List<StoreDocument> toStoreDocumentList(List<StoreDocumentRequestDTO> storeDocumentRequestDTOList);

}
