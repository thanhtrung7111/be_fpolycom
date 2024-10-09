package service.product;

import dao.ProductRepository;
import dto.product.ProductApproveRequestDTO;
import dto.product.ProductApproveResponeDTO;
import dto.product.ProductMapper;
import entity.Product;
import entity.enum_package.ProductStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductApproveResponeDTO> getAll() {
        List<ProductApproveResponeDTO> toList = ProductMapper.INSTANCE.toProductApproveResponseDTO(productRepository.findAll());
        return toList;
    }

    @Override
    public ProductApproveResponeDTO lockProduct(ProductApproveRequestDTO request) {
        Product product = productRepository.findById(Long.valueOf(request.getProductCode())).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        product.setProductStatus(ProductStatus.inActive);
        return ProductMapper.INSTANCE.toProductResponseDto(productRepository.save(product));
    }

    @Override
    public ProductApproveResponeDTO unlockProduct(ProductApproveRequestDTO request) {
        Product product = productRepository.findById(Long.valueOf(request.getProductCode())).orElseThrow(()-> new DataNotFoundException("Data Not found"));
        product.setProductStatus(ProductStatus.active);
        return ProductMapper.INSTANCE.toProductResponseDto(productRepository.save(product));

    }


}
