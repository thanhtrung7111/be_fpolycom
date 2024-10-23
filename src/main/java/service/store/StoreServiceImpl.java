package service.store;

import dao.DistrictRepository;
import dao.StoreRepository;
import dao.UserAccountRepository;
import dto.store.StoreMapper;
import dto.store.StoreRegisterRequestDTO;
import dto.store.StoreRegisterResponseDTO;
import dto.store.UserStoreDetailResponseDTO;
import dto.store_account.*;
import entity.*;
import dto.store.*;
import entity.Store;
import entity.UserAccount;
import entity.enum_package.DocumentType;
import entity.enum_package.StoreStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public StoreRegisterResponseDTO updateRegisterStore(StoreRegisterRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new UsernameNotFoundException("Tai khoan nguoi dung khong ton tai!"));


        Store store = StoreMapper.INSTANCE.toStore(requestDTO);
        store.setUserAccount(userAccount);
        store.setStoreStatus(StoreStatus.pending);
        store.getStoreDocumentList().forEach(item -> item.setStore(store));
        store.getStoreDocumentList().forEach(item -> item.setDocumentType(DocumentType.image));
        store.setUpdatedDate(new Date());
        store.setDeleted(false);
        store.setDeletedDate(null);
        storeRepository.save(store);
        return StoreMapper.INSTANCE.toStoreRegisterResponseDto(store);
    }

    @Override
    public StoreRegisterResponseDTO getRegisterStore(String userLogin,Long storeCode) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        Store store = storeRepository.findByUserAccount(userLoginExtract).orElseThrow(()->new DataNotFoundException("Cua hang khong ton tai!"));
        return StoreMapper.INSTANCE.toStoreRegisterResponseDto(store);
    }

    @Override
    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode) {
        return StoreMapper.INSTANCE.toUserStoreDetailResponseDto(storeRepository.findStoreByCodeAndStatus(storeCode, StoreStatus.active).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!")));
    }

    @Override
    public ChangeStorePasswordResponseDTO changeStorePassword(ChangeStorePasswordRequestDTO requestDTO) {
        Store store = storeRepository.findStoreByCode(Long.valueOf(requestDTO.getStoreCode()));
        if(!new BCryptPasswordEncoder().matches(requestDTO.getCurrentPassword(), store.getPassword())) {
            throw new DataNotFoundException("Mat khau hien tai khong dung !!!");
        }
        if(!requestDTO.getNewPassword().matches(requestDTO.getConfirmPassword())){
            throw new DataNotFoundException("Xac nhan mat khau khong dung !!!");
        }
        store.setPassword(new BCryptPasswordEncoder().encode(requestDTO.getNewPassword()) );
        storeRepository.save(store);
        return StoreAccountMapper.INSTANCE.changeStorePasswordDTO(store);
    }

    @Override
    public ChangeInfoStoreResponseDTO changeInfoStore(ChangeInfoStoreRequestDTO requestDTO) {
        Store store = storeRepository.findStoreByCode(Long.valueOf(requestDTO.getStoreCode()));
        if (requestDTO.getName() != null && !requestDTO.getName().isBlank()){
            store.setName(requestDTO.getName());
        }
        if (requestDTO.getImage() != null && !requestDTO.getImage().isBlank()){
            store.setImage(requestDTO.getImage());
        }
        if (requestDTO.getPhone() != null && !requestDTO.getPhone().isBlank()){
            store.setPhone(requestDTO.getPhone());
        }
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isBlank()){
            store.setEmail(requestDTO.getEmail());
        }
        if (requestDTO.getAddress() != null && !requestDTO.getAddress().isBlank()){
            store.setAddress(requestDTO.getAddress());
        }
        if (requestDTO.getAddressDetail() != null && !requestDTO.getAddressDetail().isBlank()){
            store.setAddressDetail(requestDTO.getAddressDetail());
        }
        if (requestDTO.getBannerImage() != null && !requestDTO.getBannerImage().isBlank()){
            store.setBannerImage(requestDTO.getBannerImage());
        }
        if (requestDTO.getDistrictCode() != null){
            store.setDistrict(District.builder().id(Long.valueOf(requestDTO.getDistrictCode())).build());
        }
        if (requestDTO.getWardCode() != null){
            store.setWard(Ward.builder().id(Long.valueOf(requestDTO.getWardCode())).build());
        }
        if (requestDTO.getProvinceCode() != null){
            store.setProvince(Province.builder().id(Long.valueOf(requestDTO.getProvinceCode())).build());
        }
        storeRepository.saveAndFlush(store);
        return StoreAccountMapper.INSTANCE.changeInfoStoreDTO(store);

    public List<StoreResponseDTO> getAllStoreByStatus(StoreStatus storeStatus) {
        return StoreMapper.INSTANCE.toUserStoreResponseDtoList(storeRepository.findAllStoreByStatus(storeStatus));
    }

    @Override
    public List<StoreResponseDTO> getAll() {
        return StoreMapper.INSTANCE.toUserStoreResponseDtoList(storeRepository.findAll());
    }

    @Override
    public StoreResponseDTO approveStore(Long storeCode) {
        Store store = storeRepository.findById(storeCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        store.setUpdatedDate(new Date());
        store.setStoreStatus(StoreStatus.active);
        storeRepository.save(store);
        return StoreMapper.INSTANCE.toUserStoreResponseDto(store);
    }

    @Override
    public StoreResponseDTO lockStore(Long storeCode) {
        Store store = storeRepository.findById(storeCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        store.setUpdatedDate(new Date());
        store.setStoreStatus(StoreStatus.lock);
        storeRepository.save(store);
        return StoreMapper.INSTANCE.toUserStoreResponseDto(store);
    }


}
