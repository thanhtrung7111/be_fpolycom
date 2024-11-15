package service.user_account;

import dto.user_account.*;

import java.util.List;

public interface AdminUserAccountService<DTORequest,DTOResponse> {
    public List<DTORequest> getAll();


    AdminUserAccountResponseDTO lockUser(AdminUserAccountRequestDTO request);

    AdminUserAccountResponseDTO unlockUser(AdminUserAccountRequestDTO request);

    List<UserCreateByMonthResponseDTO> findUserCreateByYear(DashboardRequestDTO request);

    List<UserCreateDayByDayResponseDTO> findUserCreateDayByDay(DashboardRequestDTO request);

    List<UserCreateByYearResponseDTO> findUserCreateBetweenYear(DashboardRequestDTO request);

    List<NumberOfOrdersByMonthResponseDTO> findOrdersByYear(DashboardRequestDTO request);

    List<NumberOfOrdersByYearResponseDTO> findOrdersBetweenYear(DashboardRequestDTO request);

    List<NumberOfOrdersDayByDayResponseDTO> findOrdersDayByDay(DashboardRequestDTO request);

    List<RevenueByStoreInDayResponseDTO> findRevenueByStoreInDay(DashboardRequestDTO request);

    List<RevenueByStoreInMonthResponseDTO> findRevenueByStoreInMonth(DashboardRequestDTO request);

    List<RevenueByStoreInYearResponseDTO> findRevenueByStoreInYear(DashboardRequestDTO request);
}
