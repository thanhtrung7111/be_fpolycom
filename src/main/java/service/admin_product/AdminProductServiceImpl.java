package service.admin_product;

import dao.ProductRepository;
import dto.admin_product.AdminProductMapper;
import dto.admin_product.AdminProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminProductServiceImpl implements  AdminProductService{
    @Autowired
    ProductRepository productRepository;

    public List<AdminProductResponseDTO> getAllProduct() {
        List<AdminProductResponseDTO> toList = AdminProductMapper.INSTANCE.toList(productRepository.findAll());
        return toList;
    }
}
