package service.product;

import dto.product.ProductInfoResponseDTO;
import dto.product.ProductRequestDTO;
import dto.product.UserProductResponseDTO;
import entity.enum_package.ProductStatus;

import java.util.List;

public interface ProductService {

    public List<UserProductResponseDTO> getAll();

    public List<UserProductResponseDTO> getALlProductByStoreAndStatus(Long storeCode,ProductStatus productStatus);

    public UserProductResponseDTO lockProduct(Long productCode);

    public UserProductResponseDTO unlockProduct(Long productCode);

    public List<UserProductResponseDTO> getALlProductByStatus(ProductStatus productStatus);

    public List<UserProductResponseDTO> getALlProductByStore(Long storeCode);

    public ProductInfoResponseDTO getProductByIdAndUserLogin(Long productCode,String userLogin);
    public ProductInfoResponseDTO getProductById(Long productCode);

    public ProductInfoResponseDTO postNew(ProductRequestDTO requestDTO);

    public ProductInfoResponseDTO updateData(ProductRequestDTO requestDTO);

}
