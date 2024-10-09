package service.liked_product;

import dao.LikedRepository;
import dao.ProductRepository;
import dao.UserAccountRepository;
import dto.liked_product.LikedMapper;
import dto.liked_product.LikedProductRequestDTO;
import dto.liked_product.LikedProductResponseDTO;
import entity.Liked;
import entity.Product;
import entity.UserAccount;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;

@Service
public class LikedProductServiceImpl implements LikedProductService {


    @Autowired
    LikedRepository likedRepository;


    @Autowired
    AuthUserService authUserService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public List<LikedProductResponseDTO> getAllLikedProduct(LikedProductRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen truy cap vao du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        return LikedMapper.INSTANCE.toLikedproLikedProductResponseDtoList(likedRepository.findAllByUserLogin(userLoginExtract));
    }

    @Override
    public LikedProductResponseDTO postLikedProduct(LikedProductRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen truy cap vao du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        Product product = productRepository.findById(requestDTO.getProductCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new DataNotFoundException("Khong ti thay du lieu!"));
        Liked likedFind = likedRepository.findLikedByUserLoginAndProduct(userLoginExtract, requestDTO.getProductCode()).orElseGet(() -> null);
        if (likedFind != null) {
            throw new DataNotFoundException("Ban da thich bai viet nay!");
        }
        Liked liked = Liked.builder().userAccount(userAccount).product(product).createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).build();
        likedRepository.save(liked);
        return LikedMapper.INSTANCE.toLikedProductResponseDto(liked);
    }

    @Override
    public LikedProductResponseDTO postUnlikedProduct(LikedProductRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen truy cap vao du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        Product product = productRepository.findById(requestDTO.getProductCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(() -> new DataNotFoundException("Khong ti thay du lieu!"));
        Liked likedFind = likedRepository.findLikedByUserLoginAndProduct(userLoginExtract, requestDTO.getProductCode()).orElseThrow(() -> new DataNotFoundException("Khong the thuc hien hanh dong thichs bai viet!"));

        likedRepository.delete(likedFind);
        return LikedMapper.INSTANCE.toLikedProductResponseDto(likedFind);
    }
}
