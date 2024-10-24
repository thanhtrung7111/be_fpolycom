package service.store_banner;

import dto.store_banner.StoreBannerRequest;
import dto.store_banner.StoreBannerResponse;
import service.common.CommonService;

import java.util.List;

public interface StoreBannerService extends CommonService<StoreBannerRequest, StoreBannerResponse,Long> {


    public List<StoreBannerResponse> getAllStoreBannerByStore(Long storeCode);
}
