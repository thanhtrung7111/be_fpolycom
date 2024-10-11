package service.product;

import dto.product.ProductInfoResponseDTO;
import dto.product.UserProductResponseDTO;
import entity.enum_package.ProductStatus;

import java.util.List;

public interface ProductService {


    public List<UserProductResponseDTO> getALlProductByStatus(ProductStatus productStatus);

    public List<UserProductResponseDTO> getALlProductByStore(Long storeCode);

    public ProductInfoResponseDTO getProductById(Long productCode,String userLogin);
}
