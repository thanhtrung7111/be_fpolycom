package service.store;

import dto.store.*;
import dto.store_account.ChangeInfoStoreRequestDTO;
import dto.store_account.ChangeInfoStoreResponseDTO;
import dto.store_account.ChangeStorePasswordRequestDTO;
import dto.store_account.ChangeStorePasswordResponseDTO;
import entity.enum_package.StoreStatus;
import java.util.List;

public interface StoreService {


    public StoreRegisterResponseDTO registerStore(StoreRegisterRequestDTO requestDTO);


    public StoreRegisterResponseDTO updateRegisterStore(StoreRegisterRequestDTO requestDTO);

    public StoreRegisterResponseDTO getRegisterStore(String userLogin,Long storeCode);

    public StoreRegisterResponseDTO getDetailStore(Long storeCode);

    public UserStoreDetailResponseDTO getStoreByCode(Long storeCode,String userLogin);


    public ChangeStorePasswordResponseDTO changeStorePassword(ChangeStorePasswordRequestDTO requestDTO);

    public ChangeInfoStoreResponseDTO changeInfoStore(ChangeInfoStoreRequestDTO requestDTO);
    public List<StoreResponseDTO> getAllStoreByStatus(StoreStatus storeStatus);

    public List<StoreResponseDTO> getAllStoreByStatusAndKeyword(StoreStatus storeStatus,String keyword);

    public List<StoreResponseDTO> getAll();


    public StoreResponseDTO approveStore(Long storeCode);

    public StoreResponseDTO lockStore(Long storeCode);

    public StoreResponseDTO rejectStore(Long storeCode,String rejectReason);

    public List<RevenueByMonthResponseDTO> revenueByMonthInYear(DashboardRequestDTO requestDTO);
    public List<RevenueYearsResponseDTO> revenueYears(DashboardRequestDTO requestDTO);

    public RevenueByMonthResponseDTO revenueByMonth(DashboardRequestDTO requestDTO);

    public RevenueYearsResponseDTO revenueByYear(DashboardRequestDTO requestDTO);
    public List<RevenueDayResponseDTO> revenuelDayByDay(DashboardRequestDTO requestDTO);

    public  List<Top5ProductBestSellerResponseDTO> top5ProductBestSeller(DashboardRequestDTO request);

    public RevenueDayResponseDTO searchRevenuelDayByDay(DashboardRequestDTO requestDTO);

    public List<Top5ProductBestSellerResponseDTO> findProductByTypeGood(DashboardRequestDTO requestDTO);

    public List<NumberOfProductByTypeResponseDTO> NumberOfProductByType(DashboardRequestDTO requestDTO);


}
