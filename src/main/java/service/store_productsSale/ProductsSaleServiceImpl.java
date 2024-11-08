package service.store_productsSale;

import dao.ProductDetailRepository;
import dto.store_productSale.ProductsSaleMapper;
import dto.store_productSale.ProductsSaleRequestDTO;
import dto.store_productSale.ProductsSaleResponeDTO;
import entity.Product;
import entity.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsSaleServiceImpl implements ProductsSaleService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductsSaleMapper productMapper;


    @Override
    public List<ProductsSaleResponeDTO> productTopSelling(Long storeCode,String typeGoodName) {
        return productMapper.INSTANCE.toProductSalesResponseDTOList(productDetailRepository.findTop5SoldProductsByStoreAndCategory(storeCode,typeGoodName));
    }
}
