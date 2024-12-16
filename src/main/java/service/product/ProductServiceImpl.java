package service.product;

import dao.LikedRepository;
import dao.ProductRepository;
import dto.product.ProductInfoResponseDTO;
import dto.product.ProductMapper;
import dto.product.ProductRequestDTO;
import dto.product.UserProductResponseDTO;
import entity.Liked;
import entity.Product;
import entity.enum_package.ProductStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_store.AuthStoreService;
import service.auth_user.AuthUserService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    LikedRepository likedRepository;

    @Autowired
    AuthStoreService authStoreService;

    @Override
    public List<UserProductResponseDTO> getALlProductByStatus(ProductStatus productStatus) {
        return ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.getAllProductByStatus(productStatus));
    }

    @Override
    public List<UserProductResponseDTO> getALlProductByStatusAndKeyWord(ProductStatus productStatus, String keyWord) {

        return ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.getAllProductByStatusAndKeyword(productStatus,keyWord));
    }

    @Override
    public List<UserProductResponseDTO> getALlProductByStore(Long storeCode) {
        return ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.getAllProductByStore(storeCode));
    }

    @Override
    public List<UserProductResponseDTO> getALlProductByStoreAndStatus(Long storeCode,ProductStatus productStatus) {
        return ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.getAllProductByStoreAndStatus(storeCode,productStatus));
    }

    @Override
    public ProductInfoResponseDTO getProductByIdAndUserLogin(Long productCode, String userLogin) {
        String userLoginExtract = null;
        ProductInfoResponseDTO responseDTO = ProductMapper.INSTANCE.toProductInfoResponseDto(productRepository.findByProductByStatus(productCode, ProductStatus.active).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai")));
        if (userLogin != null && !userLogin.isBlank()) {
            userLoginExtract = authUserService.extractUserlogin(userLogin);
            Optional<Liked> liked = likedRepository.findLikedByUserLoginAndProduct(userLoginExtract, responseDTO.getProductCode());
            if (liked.isPresent()) {
                responseDTO.setLiked(true);
            }
        }
        return responseDTO;
    }

    @Override
    public ProductInfoResponseDTO getProductById(Long productCode) {
        return ProductMapper.INSTANCE.toProductInfoResponseDto(productRepository.findById(productCode).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!")));
    }


    @Override
    public List<UserProductResponseDTO> getAll() {
        List<UserProductResponseDTO> toList = ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.findAll());
        return toList;
    }

    @Override
    public UserProductResponseDTO lockProduct(Long productCode) {
        Product product = productRepository.findById(productCode).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        product.setProductStatus(ProductStatus.lock);
        return ProductMapper.INSTANCE.toUserProductResponseDto(productRepository.save(product));
    }

    @Override
    public UserProductResponseDTO unlockProduct(Long productCode) {
        Product product = productRepository.findById(productCode).orElseThrow(()-> new DataNotFoundException("Data Not found"));
        product.setProductStatus(ProductStatus.active);
        return ProductMapper.INSTANCE.toUserProductResponseDto(productRepository.save(product));

    }


    @Override
    public ProductInfoResponseDTO postNew(ProductRequestDTO requestDTO) {
        if (authStoreService.isValidStore(requestDTO.getStoreCode())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        Product product = ProductMapper.INSTANCE.toProduct(requestDTO);
        product.getProductDetailList().forEach(item -> item.setProduct(product));
        product.getProductAttrList().forEach(item -> item.setProduct(product));
        product.setProductStatus(ProductStatus.pending);
        Product productSaved = productRepository.save(product);
        System.out.println(productSaved.getName());
//        Product product1 = productRepository.findById(productSaved.getId()).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai"));
        return ProductMapper.INSTANCE.toProductInfoResponseDto(productSaved);
    }

    @Override
    public ProductInfoResponseDTO updateData(ProductRequestDTO requestDTO) {
        if (authStoreService.isValidStore(requestDTO.getStoreCode())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        Product productFind = productRepository.findById(requestDTO.getProductCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        Product product = ProductMapper.INSTANCE.toProduct(requestDTO);
        product.getProductDetailList().forEach(item -> item.setProduct(product));
        product.getProductAttrList().forEach(item -> item.setProduct(product));
        product.setProductStatus(productFind.getProductStatus());
        productRepository.save(product);
        Product product1 = productRepository.findById(requestDTO.getProductCode()).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai"));
        return ProductMapper.INSTANCE.toProductInfoResponseDto(product1);
    }
}
