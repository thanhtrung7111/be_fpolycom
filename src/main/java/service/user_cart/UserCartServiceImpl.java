package service.user_cart;

import dao.ProductDetailRepository;
import dao.ShoppingCartRepository;
import dao.UserAccountRepository;
import dto.user_cart.UserCartMapper;
import dto.user_cart.UserCartRequestDTO;
import dto.user_cart.UserCartResponseDTO;
import entity.ProductDetail;
import entity.ShoppingCart;
import entity.UserAccount;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserCartServiceImpl implements UserCartService{

    @Autowired
    AuthUserService authUserService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;


    @Override
    public List<UserCartResponseDTO> getAllCartByUser(UserCartRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        return UserCartMapper.INSTANCE.toUserCartResponseDtoList(shoppingCartRepository.findShoppingCartByUserLogin(userLoginExtract));
    }

    @Override
    public UserCartResponseDTO postCartByUser(UserCartRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new UsernameNotFoundException("Tai khoan nguoi dung khong ton tai!"));
        ProductDetail productDetail = productDetailRepository.findById(requestDTO.getProductDetailCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        Optional<ShoppingCart> shoppingCartFind = shoppingCartRepository.findShoppingCartByUserLoginAndPrdcDetail(userLoginExtract, requestDTO.getProductDetailCode());
        if(shoppingCartFind.isPresent()){
            throw new DataNotFoundException("Gio hang da ton tai");
        }
        ShoppingCart shoppingCart = ShoppingCart.builder().createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).productDetail(productDetail).userAccount(userAccount).quantity(requestDTO.getQuantity()).build();
        shoppingCartRepository.save(shoppingCart);
        return UserCartMapper.INSTANCE.toUserCartResponseDto(shoppingCart);
    }

    @Override
    public UserCartResponseDTO updateCartByUser(UserCartRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserLoginAndPrdcDetail(userLoginExtract, requestDTO.getProductDetailCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        shoppingCart.setQuantity(requestDTO.getQuantity());
        shoppingCart.setUpdatedDate(new Date());
        shoppingCartRepository.save(shoppingCart);
        return UserCartMapper.INSTANCE.toUserCartResponseDto(shoppingCart);
    }

    @Override
    public UserCartResponseDTO deleteCartByUser(UserCartRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserLoginAndPrdcDetail(userLoginExtract, requestDTO.getProductDetailCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        shoppingCartRepository.delete(shoppingCart);
        return UserCartMapper.INSTANCE.toUserCartResponseDto(shoppingCart);
    }
}
