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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductApproveResponeDTO postData(ProductApproveRequestDTO productApproveRequestDTO) {
        // Chuyển từ DTO sang entity
        Product product = ProductMapper.INSTANCE.toProduct(productApproveRequestDTO);

        // Lưu sản phẩm mới vào cơ sở dữ liệu
        product = productRepository.save(product);

        // Chuyển từ entity sang DTO để trả về response
        return ProductMapper.INSTANCE.toProductResponseDto(product);
    }

    @Override
    public ProductApproveResponeDTO updateData(ProductApproveRequestDTO productApproveRequestDTO) {
        // Tìm sản phẩm theo productCode
        Product product = productRepository.findById(productApproveRequestDTO.getProductCode())
                .orElseThrow(() -> new DataNotFoundException("Product not found"));

        // Cập nhật các trường thông tin
        product.setName(productApproveRequestDTO.getName());
        product.setProductStatus(ProductStatus.active);
        product.setUpdatedDate(new Date());

        // Lưu lại vào cơ sở dữ liệu
        productRepository.save(product);

        // Trả về thông tin đã được cập nhật dưới dạng DTO
        return ProductMapper.INSTANCE.toProductResponseDto(product);
    }

    @Override
    public ProductApproveResponeDTO deleteData(ProductApproveRequestDTO productApproveRequestDTO) {
        // Tìm sản phẩm theo productCode
        Product product = productRepository.findById(productApproveRequestDTO.getProductCode())
                .orElseThrow(() -> new DataNotFoundException("Product not found"));

        // Đánh dấu sản phẩm là đã xóa
        product.setDeleted(true);
        product.setDeletedDate(new Date());

        // Lưu lại thay đổi vào cơ sở dữ liệu
        productRepository.save(product);

        // Trả về DTO của sản phẩm đã bị xóa
        return ProductMapper.INSTANCE.toProductResponseDto(product);
    }

    @Override
    public List<ProductApproveResponeDTO> getAllData() {
        // Lấy toàn bộ sản phẩm từ cơ sở dữ liệu và chuyển sang DTO
        List<Product> products = productRepository.findAll(); // Lấy danh sách sản phẩm từ CSDL
        return products.stream()
                .map(ProductMapper.INSTANCE::toProductResponseDto) // Chuyển từng Product thành DTO
                .collect(Collectors.toList()); // Thu thập thành danh sách các DTO
    }


    @Override
    public ProductApproveResponeDTO getDetailData(Long productCode) {
        // Tìm sản phẩm theo productCode và trả về chi tiết sản phẩm dưới dạng DTO
        Product product = productRepository.findById(productCode)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));

        return ProductMapper.INSTANCE.toProductResponseDto(product);
    }
   @Override
    public List<ProductApproveResponeDTO> updatePendingProductsToActive() {
        // Lấy danh sách các sản phẩm có status là "pending"
        List<Product> pendingProducts = productRepository.findByProductStatus("pending");

        // Cập nhật status của tất cả sản phẩm thành "active"
        pendingProducts.forEach(product -> product.setProductStatus(ProductStatus.valueOf("active")));

        // Lưu các sản phẩm đã cập nhật vào CSDL
        productRepository.saveAll(pendingProducts);

        // Trả về danh sách DTO của các sản phẩm đã được cập nhật
        return pendingProducts.stream()
                .map(ProductMapper.INSTANCE::toProductResponseDto)
                .collect(Collectors.toList());
    }

}
