package store_controller;

import dto.store_banner.StoreBannerRequest;
import dto.voucher.VoucherRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store_banner.StoreBannerService;

@RestController
@RequestMapping(value = "/store")
public class StoreBannerController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreBannerService storeBannerService;

    @PostMapping(value = "/banner/all")
    public ResponseEntity<Object> allBanner(@RequestBody StoreBannerRequest request) {
        return ResponseEntity.ok(dataReturnService.success(storeBannerService.getAllStoreBannerByStore(request.getStoreCode())));
    }

    @PostMapping(value = "/banner/new")
    public ResponseEntity<Object> newBanner(@RequestBody @Valid StoreBannerRequest request) {
        return ResponseEntity.ok(dataReturnService.success(storeBannerService.postData(request)));
    }

    @PostMapping(value = "/banner/update")
    public ResponseEntity<Object> updateBanner(@RequestBody @Valid StoreBannerRequest request) {
        return ResponseEntity.ok(dataReturnService.success(storeBannerService.updateData(request)));
    }

    @PostMapping(value = "/banner/delete")
    public ResponseEntity<Object> deleteBanner(@RequestBody StoreBannerRequest request) {
        return ResponseEntity.ok(dataReturnService.success(storeBannerService.deleteData(request)));
    }
}
