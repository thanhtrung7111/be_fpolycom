package service.store_document;

import dao.StoreDocumentRepository;
import dto.store.StoreDocumentMapper;
import dto.store.StoreDocumentResponseDTO;
import entity.StoreDocument;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreDocumentServiceImpl implements StoreDocumentService {


    @Autowired
    StoreDocumentRepository  storeDocumentRepository;

    @Override
    public StoreDocumentResponseDTO removeData(Long storeDocumentCode) {
        StoreDocument storeDocument = storeDocumentRepository.findById(storeDocumentCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        storeDocumentRepository.deleteById(storeDocumentCode);
        return StoreDocumentMapper.INSTANCE.toStoreDocumentResponseDto(storeDocument);
    }
}
