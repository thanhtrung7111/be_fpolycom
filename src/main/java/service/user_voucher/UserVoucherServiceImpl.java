package service.user_voucher;

import dao.UserAccountRepository;
import dao.VoucherRepository;
import dao.VoucherUserRepository;
import dto.voucher_user.VoucherUserMapper;
import dto.voucher_user.VoucherUserRequestDTO;
import dto.voucher_user.VoucherUserResponseDTO;
import entity.UserAccount;
import entity.Voucher;
import entity.VoucherUser;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;

@Service
public class UserVoucherServiceImpl implements UserVoucherService {

    @Autowired
    VoucherUserRepository voucherUserRepository;

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public List<VoucherUserResponseDTO> getAllVoucherByUser(VoucherUserRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        return VoucherUserMapper.mapper.toVoucherUserResponseDtoList(voucherUserRepository.getAllVoucherUser(userLoginExtract));
    }

    @Override
    public VoucherUserResponseDTO postUserVoucher(VoucherUserRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new UsernameNotFoundException("Nguoi dung khong ton tai"));
        Voucher voucher  = voucherRepository.findById(requestDTO.getVoucherCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        VoucherUser voucherUser = VoucherUser.builder().userAccount(userAccount).voucher(voucher).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).build();
        voucherUserRepository.save(voucherUser);
        return VoucherUserMapper.mapper.toVoucherUserResponseDto(voucherUser);
    }
}
