package store_controller;

import dto.store.DashboardRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturn;
import service.data_return.DataReturnService;
import service.store.StoreService;

@RestController
@RequestMapping(value = "/store")
public class DashboardStoreController {
    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreService storeService;

    @PostMapping(value = "revenueByMonthInYear")
    public ResponseEntity<DataReturn> revenueByMonthInYear(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.revenueByMonthInYear(request)));
    }
    @PostMapping(value = "revenue-between-year")
    public ResponseEntity<DataReturn> revenueByYears(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.revenueYears(request)));
    }
    @PostMapping(value = "revenueByMonth")
    public ResponseEntity<DataReturn> revenueByMonth(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.revenueByMonth(request)));
    }
    @PostMapping(value = "revenueByYear")
    public ResponseEntity<DataReturn> revenueByYear(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.revenueByYear(request)));
    }
    @PostMapping(value = "revenueDayByDay")
    public ResponseEntity<DataReturn> revenueByDay(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.revenuelDayByDay(request)));
    }

    @PostMapping(value = "top5-product-best-seller")
    public ResponseEntity<DataReturn> top5productbestseller(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.top5ProductBestSeller(request)));
    }

    @PostMapping(value = "search-revenue-by-day")
    public ResponseEntity<DataReturn> searchRevenueByDay(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.searchRevenuelDayByDay(request)));
    }
    @PostMapping(value = "search-product-by-type")
    public ResponseEntity<DataReturn> searchProductByType(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.findProductByTypeGood(request)));
    }
    @PostMapping(value = "number-of-product-by-type")
    public ResponseEntity<DataReturn> NumberOfProductByType(@RequestBody @Valid DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(storeService.NumberOfProductByType(request)));
    }


}
