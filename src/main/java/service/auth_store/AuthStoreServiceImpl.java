package service.auth_store;

import dao.StoreRepository;
import dto.auth_store.AuthStoreLoginResponseDTO;
import dto.auth_store.AuthStoreMapper;
import entity.Store;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

@Service
public class AuthStoreServiceImpl implements AuthStoreService{

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    public String getStoreLoginAuthenication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                System.out.println(principal.toString());
                return principal.toString();  // Trong một số trường hợp có thể là chuỗi username trực tiếp
            }
        }
        throw new UsernameNotFoundException("Nguoi dung chua xac thuc!");
    }

    @Override
    public AuthStoreLoginResponseDTO getStoreByUser(String userLogin) {
        System.out.println(userLogin+"serviceStore");
        Store store = storeRepository.findByUserAccount(authUserService.extractUserlogin(userLogin)).orElseThrow(()->new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        System.out.println(store.getName()+"serviceStore");
        return AuthStoreMapper.INSTANCE.toAuthStoreLoginResponseDto(store);
    }

    @Override
    public boolean  isValidStore(Long storeID) {
        System.out.println(getStoreLoginAuthenication()+"-----------------------");
        Store store = storeRepository.findByUserAccount(getStoreLoginAuthenication()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai!"));
        return !storeID.equals(store.getId());
    }
}
