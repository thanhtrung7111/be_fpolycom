package service.store_productsSale;

import dto.store_productSale.ProductsSaleRequestDTO;
import dto.store_productSale.ProductsSaleResponeDTO;

import java.util.List;

public interface ProductsSaleService {

    public List<ProductsSaleResponeDTO> productTopSelling(Long storeCode, String typeGoodName);
}
