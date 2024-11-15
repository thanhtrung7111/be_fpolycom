package admin_controller;


import dto.user_account.DashboardRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.user_account.AdminUserAccountService;

@RestController
@RequestMapping(value = "/admin")
public class DashboardAdminController {
    @Autowired
    AdminUserAccountService adminUserAccountService;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping("/user-by-year")
    public ResponseEntity<Object> findUserCreateByYear(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findUserCreateByYear(request)));
    }

    @PostMapping("/user-day-by-day")
    public ResponseEntity<Object> findUserCreateDayByDay(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findUserCreateDayByDay(request)));
    }

    @PostMapping("/user-between-year")
    public ResponseEntity<Object> findUserCreateBetweenYear(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findUserCreateBetweenYear(request)));
    }

    @PostMapping("/order-by-year")
    public ResponseEntity<Object> findOrdersByYear(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findOrdersByYear(request)));
    }

    @PostMapping("/order-between-year")
    public ResponseEntity<Object> findOrdersBetweenYear(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findOrdersBetweenYear(request)));
    }

    @PostMapping("/order-day-by-day")
    public ResponseEntity<Object> findOrdersDayByDay(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findOrdersDayByDay(request)));
    }

    @PostMapping("/top10-revenue-store-day")
    public ResponseEntity<Object> findRevenueByStoreInDay(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findRevenueByStoreInDay(request)));
    }

    @PostMapping("/top10-revenue-store-month")
    public ResponseEntity<Object> findRevenueByStoreInMonth(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findRevenueByStoreInMonth(request)));
    }

    @PostMapping("/top10-revenue-store-year")
    public ResponseEntity<Object> findRevenueByStoreInYear(@RequestBody DashboardRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(adminUserAccountService.findRevenueByStoreInYear(request)));
    }

}
