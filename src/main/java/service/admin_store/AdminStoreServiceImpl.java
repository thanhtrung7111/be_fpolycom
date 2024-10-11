package service.admin_store;

import dao.StoreRepository;
import dto.admin_store.AdminStoreMapper;
import dto.admin_store.AdminStoreResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminStoreServiceImpl implements AdminStoreService {
    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<AdminStoreResponseDTO> getAllStore() {
        List<AdminStoreResponseDTO> toList = AdminStoreMapper.INSTANCE.toList(storeRepository.findAll());
        return toList;
    }

}
