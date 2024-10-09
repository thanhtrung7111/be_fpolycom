package dto.store;

import entity.StoreDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);


    StoreDocument toStoreDocument(StoreDocumentRequestDTO requestDTO);

    List<StoreDocument> toStoreDocumentList(List<StoreDocumentRequestDTO> storeDocumentRequestDTOList);




}
