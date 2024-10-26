package service.store_banner;

import dao.StoreBannerRepository;
import dto.store_banner.StoreBannerMapper;
import dto.store_banner.StoreBannerRequest;
import dto.store_banner.StoreBannerResponse;
import entity.Product;
import entity.StoreBanner;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreBannerServiceImpl implements StoreBannerService{

    @Autowired
    StoreBannerRepository storeBannerRepository;


    @Override
    public StoreBannerResponse postData(StoreBannerRequest request) {
        StoreBanner storeBanner = StoreBannerMapper.INSTANCE.toStoreBanner(request);
        storeBannerRepository.save(storeBanner);
        return StoreBannerMapper.INSTANCE.toStoreBannerResponse(storeBanner);
    }

    @Override
    public StoreBannerResponse updateData(StoreBannerRequest request) {
        StoreBanner storeBanner = storeBannerRepository.findById(request.getStoreBannerCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        storeBanner.setImage(request.getImage());
        storeBanner.setProduct(Product.builder().id(request.getProductCode()).build());
        storeBanner.setStatus(request.getStatus());
        storeBanner.setTitle(request.getTitle());
        storeBanner.setBannerPosition(request.getBannerPosition());
//        storeBanner.setMain(request.);
        storeBannerRepository.save(storeBanner);
        return StoreBannerMapper.INSTANCE.toStoreBannerResponse(storeBanner);
    }

    @Override
    public StoreBannerResponse deleteData(StoreBannerRequest request) {
        StoreBanner storeBanner = storeBannerRepository.findById(request.getStoreBannerCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        storeBannerRepository.delete(storeBanner);
        return StoreBannerMapper.INSTANCE.toStoreBannerResponse(storeBanner);
    }

    @Override
    public List<StoreBannerResponse> getAllData() {
        return StoreBannerMapper.INSTANCE.toStoreBannerResponseList(storeBannerRepository.findAll());
    }

    @Override
    public StoreBannerResponse getDetailData(Long aLong) {
        return null;
    }

    @Override
    public List<StoreBannerResponse> getAllStoreBannerByStore(Long storeCode) {
        return StoreBannerMapper.INSTANCE.toStoreBannerResponseList(storeBannerRepository.findAllStoreBannerByStore(storeCode));
    }
}
