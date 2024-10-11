package service.store;

import dao.StoreRepository;
import dao.UserAccountRepository;
import dto.store.StoreMapper;
import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;
import dto.store.UserStoreDetailResponseDTO;
import entity.Store;
import entity.UserAccount;
import entity.enum_package.DocumentType;
import entity.enum_package.StoreStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;


    @Override
    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Tai khoan nguoi dung khong ton tai!"));
        if (userAccount.getStore() != null) {
            throw new DataNotFoundException("Ban da dang ki cua hang");
        }
        Store store = StoreMapper.INSTANCE.toStore(requestDTO);
        store.setUserAccount(userAccount);
        store.setId(null);
        store.setStoreStatus(StoreStatus.pending);
        store.getStoreDocumentList().forEach(item -> item.setStore(store));
        store.getStoreDocumentList().forEach(item -> item.setDocumentType(DocumentType.image));
        store.setCreatedDate(new Date());
        store.setUpdatedDate(null);
        store.setDeleted(false);
        store.setDeletedDate(null);
        storeRepository.save(store);
        return StoreMapper.INSTANCE.toStoreRegisterResponseDto(store);
    }

    @Override
    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode) {
        return StoreMapper.INSTANCE.toUserStoreDetailResponseDto(storeRepository.findStoreByCodeAndStatus(storeCode, StoreStatus.active).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!")));
    }
}
