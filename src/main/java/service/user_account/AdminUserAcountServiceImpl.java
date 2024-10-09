package service.user_account;

import dao.UserAccountRepository;
import dto.user_account.AdminUserAccountRequestDTO;
import dto.user_account.AdminUserAccountResponseDTO;
import dto.user_account.UserAccountMapper;
import entity.UserAccount;
import entity.enum_package.UserStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserAcountServiceImpl implements AdminUserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public List<AdminUserAccountResponseDTO> getAll() {
        List<AdminUserAccountResponseDTO> toList = UserAccountMapper.INSTANCE.toList(userAccountRepository.findAll());
        return toList;
    }

    @Override
    public AdminUserAccountResponseDTO lockUser(AdminUserAccountRequestDTO request) {
        UserAccount userAccount = userAccountRepository.findById(Long.valueOf(request.getUserAccountID())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        userAccount.setUserStatus(UserStatus.lock);
        return UserAccountMapper.INSTANCE.toAdminUserAccountResponseDto(userAccountRepository.save(userAccount));
    }

    @Override
    public AdminUserAccountResponseDTO unlockUser(AdminUserAccountRequestDTO request) {
        UserAccount userAccount = userAccountRepository.findById(Long.valueOf(request.getUserAccountID())).orElseThrow(()-> new DataNotFoundException("Data no found"));
        userAccount.setUserStatus(UserStatus.active);
        return UserAccountMapper.INSTANCE.toAdminUserAccountResponseDto(userAccountRepository.save(userAccount));

    }
}