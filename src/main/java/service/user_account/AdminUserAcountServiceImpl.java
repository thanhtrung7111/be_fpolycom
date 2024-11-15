package service.user_account;

import dao.StoreRepository;
import dao.UserAccountRepository;
import dto.user_account.*;
import entity.Store;
import entity.UserAccount;
import entity.enum_package.UserStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserAcountServiceImpl implements AdminUserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<AdminUserAccountResponseDTO> getAll() {
        List<AdminUserAccountResponseDTO> toList = UserAccountMapper.INSTANCE.toList(userAccountRepository.findAll());
        return toList;
    }

    @Override
    public AdminUserAccountResponseDTO lockUser(AdminUserAccountRequestDTO request) {
       UserAccount userAccount = userAccountRepository.findById(Long.valueOf(request.getUserAccountID())).orElse(null);
       userAccount.setUserStatus(UserStatus.lock);
       return UserAccountMapper.INSTANCE.toAdminUserAccountResponseDto(userAccountRepository.save(userAccount));
    }

    @Override
    public AdminUserAccountResponseDTO unlockUser(AdminUserAccountRequestDTO request) {
        UserAccount userAccount = userAccountRepository.findById(Long.valueOf(request.getUserAccountID())).orElseThrow(()-> new DataNotFoundException("Data no found"));
        userAccount.setUserStatus(UserStatus.active);
        return UserAccountMapper.INSTANCE.toAdminUserAccountResponseDto(userAccountRepository.save(userAccount));

    }

    @Override
    public List<UserCreateByMonthResponseDTO> findUserCreateByYear(DashboardRequestDTO request) {
        List<UserCreateByMonthResponseDTO> res = userAccountRepository.findUserCreateByYear(request.getYear());
        return res;
    }

    @Override
    public List<UserCreateDayByDayResponseDTO> findUserCreateDayByDay(DashboardRequestDTO request) {
        List<UserCreateDayByDayResponseDTO> res = userAccountRepository.findUserCreateDayByDay(request.getStartDate(), request.getEndDate());
        return res;
    }

    @Override
    public List<UserCreateByYearResponseDTO> findUserCreateBetweenYear(DashboardRequestDTO request) {
        List<UserCreateByYearResponseDTO> res = userAccountRepository.findUserCreateBetweenYear(request.getStartYear(), request.getEndYear());
        return res;
    }

    @Override
    public List<NumberOfOrdersByMonthResponseDTO> findOrdersByYear(DashboardRequestDTO request) {
        List<NumberOfOrdersByMonthResponseDTO> res = userAccountRepository.findOrdersByYear(request.getYear());
        return res;
    }

    @Override
    public List<NumberOfOrdersByYearResponseDTO> findOrdersBetweenYear(DashboardRequestDTO request) {
        List<NumberOfOrdersByYearResponseDTO> res = userAccountRepository.findOrdersBetweenYear(request.getStartYear(), request.getEndYear());
        return res;
    }

    @Override
    public List<NumberOfOrdersDayByDayResponseDTO> findOrdersDayByDay(DashboardRequestDTO request) {

        List<NumberOfOrdersDayByDayResponseDTO> res = userAccountRepository.findOrdersDayByDay(request.getStartDate(), request.getEndDate());
        return res;
    }

    @Override
    public List<RevenueByStoreInDayResponseDTO> findRevenueByStoreInDay(DashboardRequestDTO request) {
        Pageable pageable = PageRequest.of(0,10);
        List<RevenueByStoreInDayResponseDTO> res = userAccountRepository.findRevenueByStoreInDay(request.getDate(),pageable);
        return res;
    }

    @Override
    public List<RevenueByStoreInMonthResponseDTO> findRevenueByStoreInMonth(DashboardRequestDTO request) {
        Pageable pageable = PageRequest.of(0,10);
        List<RevenueByStoreInMonthResponseDTO> res = userAccountRepository.findRevenueByStoreInMonth(request.getMonth(),request.getYear(),pageable);
        return res;
    }

    @Override
    public List<RevenueByStoreInYearResponseDTO> findRevenueByStoreInYear(DashboardRequestDTO request) {
        Pageable pageable = PageRequest.of(0,10);
        List<RevenueByStoreInYearResponseDTO> res = userAccountRepository.findRevenueByStoreInYear(request.getYear(),pageable);
        return res;
    }
}