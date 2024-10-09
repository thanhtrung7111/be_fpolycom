package service.user_bank;

import dao.BankBranchRepository;
import dao.BankUserRepository;
import dao.UserAccountRepository;
import dto.bank_user.BankUserMapper;
import dto.bank_user.BankUserRequestDTO;
import dto.bank_user.BankUserResponseDTO;
import entity.BankBranch;
import entity.BankUser;
import entity.UserAccount;
import entity.enum_package.BankStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserBankServiceImpl implements UserBankService{

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    BankBranchRepository bankBranchRepository;

    @Override
    public List<BankUserResponseDTO> getAllBankByUser(BankUserRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        return BankUserMapper.INSTANCE.toBankUserResponseDtoList(bankUserRepository.findAllBankUserByUserLogin(userLoginExtract));
    }

    @Override
    public BankUserResponseDTO postBankUser(BankUserRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new UsernameNotFoundException("Tai khoan nguoi dung khong ton tai!"));
        if(requestDTO.getPasswordBank().equals(userAccount.getPasswordBank())){
            throw new DataNotFoundException("Ban khong co quyen truy cap vao ngan hang!");
        }
        Optional<BankUser> bankUserFind = bankUserRepository.findBankUserByAccountAndBranch(requestDTO.getAccountNumber(),requestDTO.getBankCode());
        if(bankUserFind.isPresent()) throw new DataNotFoundException("Tai khoan ngan hang da ton tai!");

        BankBranch bankBranch = bankBranchRepository.findById(requestDTO.getBankBranchCode()).orElseThrow(()->new DataNotFoundException("Khong ton tai bank!"));
        BankUser bankUser = BankUserMapper.INSTANCE.toBankUser(requestDTO);
        bankUser.setBankBranch(bankBranch);
        bankUser.setUserAccount(userAccount);
        bankUser.setBankStatus(BankStatus.active);
        bankUserRepository.save(bankUser);
        return BankUserMapper.INSTANCE.toBankUserResponseDto(bankUser);
    }
}
