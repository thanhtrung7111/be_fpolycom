package service.accept_store;

import dao.AcceptStoreRepository;

import dto.accept_store.AcceptStoreMapper;
import dto.accept_store.AcceptStoreRequestDTO;
import dto.accept_store.AcceptStoreResponseDTO;
import dto.user_account.AdminUserAccountRequestDTO;
import dto.user_account.AdminUserAccountResponseDTO;
import dto.user_account.UserAccountMapper;
import entity.Store;
import entity.UserAccount;
import entity.enum_package.StoreStatus;
import entity.enum_package.UserStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcceptStoreServiceImpl implements AcceptStoreService {

    @Autowired
    AcceptStoreRepository acceptStoreRepository;

    @Override
    public List<AcceptStoreResponseDTO> getAll() {
        List<AcceptStoreResponseDTO> toList = AcceptStoreMapper.INSTANCE.toList(acceptStoreRepository.findAll());
        return toList;
    }

    @Override
    public AcceptStoreResponseDTO lockStore (AcceptStoreRequestDTO request) {
        Store store = acceptStoreRepository.findById(Long.valueOf(request.getStoreID())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        store.setStoreStatus(StoreStatus.lock);
        return AcceptStoreMapper.INSTANCE.toAcceptStoreResponseDTO(acceptStoreRepository.save(store));
    }

    @Override
    public AcceptStoreResponseDTO unlockStore (AcceptStoreRequestDTO request) {
        Store store = acceptStoreRepository.findById(Long.valueOf(request.getStoreID())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        store.setStoreStatus(StoreStatus.active);
        return AcceptStoreMapper.INSTANCE.toAcceptStoreResponseDTO(acceptStoreRepository.save(store));
    }

}
