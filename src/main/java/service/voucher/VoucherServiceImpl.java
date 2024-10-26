package service.voucher;

import dao.StoreRepository;
import dao.UserAccountRepository;
import dao.VoucherRepository;
import dto.store.StoreMapper;
import dto.store.UserStoreDetailResponseDTO;
import dto.voucher.VoucherMapper;
import dto.voucher.VoucherRequestDTO;
import dto.voucher.VoucherRespUserDTO;
import dto.voucher.VoucherResponseDTO;
import entity.Store;
import entity.UserAccount;
import entity.Voucher;
import exeception_handler.DataNotFoundException;
import jakarta.validation.ValidationException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
//import service.StoreAuthService;
import service.auth_store.AuthStoreService;
import service.auth_user.AuthUserService;
import service.store.StoreService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService{



    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    AuthStoreService authStoreService;

//    @Autowired
//    StoreAuthService storeAuthService;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;



    @Override
    public List<VoucherResponseDTO> getVoucherByStore(Long storeCode) {

        return VoucherMapper.INSTANCE.toVoucherResponseDtoList(voucherRepository.findAllVoucherByStore(storeCode));
    }

    @Override
    public List<VoucherResponseDTO> getAllVoucherByStoreID(VoucherRequestDTO request) {
//        UserDetails userDetails = storeAuthService.loadUserByUsername(request.getUserLogin());
//        if(userDetails.getUsername().isBlank()){
//          throw new DataNotFoundException("Cua hang khong ton tai hoac chua dang nhap");
//        }
        List<Voucher> voucherList = voucherRepository.findAllVoucherByStore(request.getStoreCode());
        return VoucherMapper.INSTANCE.toVoucherResponseDtoList(voucherList);
    }

    @Override
    public List<VoucherResponseDTO> getAllVoucherByStoreIDAndUser(Long storeCode, String userLogin){

        if(userLogin != null){
            String userLoginextract = authUserService.extractUserlogin(userLogin);
            Optional<UserAccount> userAccount = userAccountRepository.findByUserLogin(userLoginextract);
            if(userAccount.isEmpty()){
                return VoucherMapper.INSTANCE.toVoucherResponseDtoList( voucherRepository.findAllVoucherByStore(storeCode));
            }
           List<Voucher> voucherList = voucherRepository.findAllVoucherUserByStore(storeCode,userAccount.get());
            return VoucherMapper.INSTANCE.toVoucherResponseDtoList(voucherList);
        }else{
            List<Voucher> voucherList = voucherRepository.findAllVoucherByStore(storeCode);
            return VoucherMapper.INSTANCE.toVoucherResponseDtoList(voucherList);
        }
    }

    @Override
    public VoucherResponseDTO postData(VoucherRequestDTO voucherRequestDTO) {
        Voucher voucher = VoucherMapper.INSTANCE.toVoucher(voucherRequestDTO);
        if(voucher.getBeginDate().after(voucher.getEndDate())) throw new ValidationException("Ngay bat dau phai nho hon ngay ket thuc");
        if(!voucher.getBeginDate().after(new Date())) throw  new ValidationException("Voucher khong the cai dat ngay trong qua khu");
        return VoucherMapper.INSTANCE.toVoucherResponseDto(voucherRepository.save(voucher));
    }

    @Override
    public VoucherResponseDTO updateData(VoucherRequestDTO voucherRequestDTO) {
        Voucher check = voucherRepository.findById(voucherRequestDTO.getVoucherCode()).orElseThrow(() -> new DataNotFoundException("Voucher not found"));
        Voucher voucher = VoucherMapper.INSTANCE.toVoucher(voucherRequestDTO);
        if(voucher.getBeginDate().after(voucher.getEndDate())) throw new ValidationException("Ngay bat dau phai nho hon ngay ket thuc");
        voucher.setUpdatedDate(new Date());

        return VoucherMapper.INSTANCE.toVoucherResponseDto(voucherRepository.save(voucher));
    }

    @Override
    public VoucherResponseDTO deleteData(VoucherRequestDTO voucherRequestDTO) {
        Voucher voucher = voucherRepository.findById(voucherRequestDTO.getVoucherCode()).orElseThrow(() -> new DataNotFoundException("Voucher not found"));
        voucher.setDeleted(true);
        voucher.setDeletedDate(new Date());
        return VoucherMapper.INSTANCE.toVoucherResponseDto(voucherRepository.save(voucher));
    }

    @Override
    public List<VoucherResponseDTO> getAllData() {
        return List.of();
    }

    @Override
    public VoucherResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
