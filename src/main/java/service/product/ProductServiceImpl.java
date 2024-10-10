package service.product;

import dao.FollowedRepository;
import dao.LikedRepository;
import dao.ProductRepository;
import dto.product.ProductInfoResponseDTO;
import dto.product.ProductMapper;
import dto.product.UserProductResponseDTO;
import entity.Followed;
import entity.Liked;
import entity.enum_package.ProductStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public List<UserProductResponseDTO> getALlProductByStatus(ProductStatus productStatus) {
        return ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.getAllProductByStatus(productStatus));
    }

    @Override
    public List<UserProductResponseDTO> getALlProductByStore(Long storeCode) {
        return ProductMapper.INSTANCE.toUserProductResponseDtoList(productRepository.getAllProductByStore(storeCode));
    }

    @Override
    public ProductInfoResponseDTO getProductById(Long productCode, String userLogin) {
        String userLoginExtract = null;
        ProductInfoResponseDTO responseDTO = ProductMapper.INSTANCE.toProductInfoResponseDto(productRepository.findById(productCode).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai")));
        if (userLogin != null && !userLogin.isBlank()) {
            userLoginExtract = authUserService.extractUserlogin(userLogin);
            Optional<Liked> liked = likedRepository.findLikedByUserLoginAndProduct(userLoginExtract, responseDTO.getProductCode());
            if(liked.isPresent()){
                responseDTO.setLiked(true);
            }
        }
        return responseDTO;
    }
}
