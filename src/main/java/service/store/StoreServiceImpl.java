package service.store;

import dao.*;
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
import entity.enum_package.TypeNotifycationUser;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;
import service.common.SendMessageService;
import service.user_notify.UserNotifyService;

import java.util.Date;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PaymentWallerStoreRepository paymentWallerStoreRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    TypeGoodRepository typeGoodRepository;

   @Autowired
    UserNotifyService userNotifyService;

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
        store.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
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
    public StoreRegisterResponseDTO getDetailStore(Long storeCode) {
        return StoreMapper.INSTANCE.toStoreRegisterResponseDto(storeRepository.findStoreByCode(storeCode));
    }

    @Override
    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode,String userLogin) {
        Store store = storeRepository.findById(storeCode).orElseThrow(()-> new DataNotFoundException("KHong ton tai du lieu!"));
        UserStoreDetailResponseDTO responseDTO = StoreMapper.INSTANCE.toUserStoreDetailResponseDto(store);
        if(userLogin != null){
            String userName = authUserService.extractUserlogin(userLogin);
            UserAccount userAccount = userAccountRepository.findByUserLogin(userName).orElseThrow(()->new DataNotFoundException("Khong ton tai du lieuj"));
            if(store.getFollowedList().stream().filter(item->item.getUserAccount().getUserLogin().equals(userAccount.getUserLogin())).findFirst().isPresent()){
                responseDTO.setFollowed(true);
            }
        }
        return responseDTO;
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
        if (requestDTO.getName() != null && !requestDTO.getName().isBlank()) {
            store.setName(requestDTO.getName());
        }
        if (requestDTO.getImage() != null && !requestDTO.getImage().isBlank()) {
            store.setImage(requestDTO.getImage());
        }
        if (requestDTO.getPhone() != null && !requestDTO.getPhone().isBlank()) {
            store.setPhone(requestDTO.getPhone());
        }
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isBlank()) {
            store.setEmail(requestDTO.getEmail());
        }
        if (requestDTO.getAddress() != null && !requestDTO.getAddress().isBlank()) {
            store.setAddress(requestDTO.getAddress());
        }
        if (requestDTO.getAddressDetail() != null && !requestDTO.getAddressDetail().isBlank()) {
            store.setAddressDetail(requestDTO.getAddressDetail());
        }
        if (requestDTO.getBannerImage() != null && !requestDTO.getBannerImage().isBlank()) {
            store.setBannerImage(requestDTO.getBannerImage());
        }
        if (requestDTO.getDistrictCode() != null) {
            store.setDistrict(District.builder().id(Long.valueOf(requestDTO.getDistrictCode())).build());
        }
        if (requestDTO.getWardCode() != null) {
            store.setWard(Ward.builder().id(Long.valueOf(requestDTO.getWardCode())).build());
        }
        if (requestDTO.getProvinceCode() != null) {
            store.setProvince(Province.builder().id(Long.valueOf(requestDTO.getProvinceCode())).build());
        }
        storeRepository.saveAndFlush(store);
        return StoreAccountMapper.INSTANCE.changeInfoStoreDTO(store);
    }
    public List<StoreResponseDTO> getAllStoreByStatus(StoreStatus storeStatus) {
        return StoreMapper.INSTANCE.toUserStoreResponseDtoList(storeRepository.findAllStoreByStatus(storeStatus));
    }

    @Override
    public List<StoreResponseDTO> getAllStoreByStatusAndKeyword(StoreStatus storeStatus, String keyword) {
         return StoreMapper.INSTANCE.toUserStoreResponseDtoList(storeRepository.findAllStoreByStatusAndKeyword(storeStatus,keyword));
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
        store.setReason(null);
        storeRepository.save(store);
        if(store.getPaymentWalletStore() == null){
            PaymentWalletStore paymentWalletStore = PaymentWalletStore.builder().store(store).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).balance(0.0).password(null).build();
            paymentWallerStoreRepository.save(paymentWalletStore);
        }
        userNotifyService.sendNotifyToUser("Đăng kí thành công!","Cửa hàng của bạn đã được duyệt",store.getId().toString(), TypeNotifycationUser.store,store.getImage(),store.getUserAccount().getId());
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

    @Override
    public StoreResponseDTO rejectStore(Long storeCode, String rejectReason) {
        Store store  = storeRepository.findById(storeCode).orElseThrow(()->new DataNotFoundException("Cua hang khong ton tai"));
        store.setReason(rejectReason);
        store.setStoreStatus(StoreStatus.rejected);
        storeRepository.save(store);
        userNotifyService.sendNotifyToUser("Từ chối duyệt!","Cửa hàng "+store.getName()+" bị từ chối. Lý do: "+rejectReason,store.getId().toString(), TypeNotifycationUser.store,store.getImage(),store.getUserAccount().getId());
        return StoreMapper.INSTANCE.toUserStoreResponseDto(store);
    }

    @Override
    public List<RevenueByMonthResponseDTO> revenueByMonthInYear(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        List<RevenueByMonthResponseDTO> res = storeRepository.findAllRevenueByMonth(store,  requestDTO.getYear());
        return res;
    }
    @Override
    public List<RevenueYearsResponseDTO> revenueYears(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        List<RevenueYearsResponseDTO> res = storeRepository.findAllRevenueYears(store,requestDTO.getStartYear(),requestDTO.getEndYear());
        return res;
    }

    @Override
    public RevenueByMonthResponseDTO revenueByMonth(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        RevenueByMonthResponseDTO res = storeRepository.findRevenueByMonthAndYear(store, requestDTO.getMonth(), requestDTO.getYear());
        return res;
    }

    @Override
    public RevenueYearsResponseDTO revenueByYear(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        RevenueYearsResponseDTO res = storeRepository.findRevenueByYear(store,  requestDTO.getYear());
        return res;
    }

    @Override
    public List<RevenueDayResponseDTO> revenuelDayByDay(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        List<RevenueDayResponseDTO> res = storeRepository.findRevenueByDay(store,requestDTO.getStartDate(),requestDTO.getEndDate());
        return res;
    }

    @Override
    public List<Top5ProductBestSellerResponseDTO> top5ProductBestSeller(DashboardRequestDTO request) {
        Store store = storeRepository.findById(request.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        Pageable pageable = PageRequest.of(0,5);
        List<Top5ProductBestSellerResponseDTO> res = storeRepository.findTop5ProductBestSeller(store, pageable);
        return res;
    }

    @Override
    public RevenueDayResponseDTO searchRevenuelDayByDay(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        RevenueDayResponseDTO res = storeRepository.findRevenueByMonthAndYearAndDay(store,requestDTO.getDate());
        return res;
    }

    @Override
    public List<Top5ProductBestSellerResponseDTO> findProductByTypeGood(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        TypeGood typeGood = typeGoodRepository.findById(requestDTO.getTypeGoodCode()).orElseThrow(() -> new DataNotFoundException("khong tim thay loai hang"));
        List<Top5ProductBestSellerResponseDTO> res = storeRepository.findProductByTypeGood(store,typeGood);
        return res;
    }

    @Override
    public List<NumberOfProductByTypeResponseDTO> NumberOfProductByType(DashboardRequestDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()-> new DataNotFoundException("khong tim thay store"));
        List<NumberOfProductByTypeResponseDTO> res = storeRepository.NumberOfProductByType(store);
        return res;
    }


}
