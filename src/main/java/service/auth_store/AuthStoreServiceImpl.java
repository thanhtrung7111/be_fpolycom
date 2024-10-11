package service.auth_store;

import dao.StoreRepository;
import dto.auth_store.AuthStoreLoginResponseDTO;
import dto.auth_store.AuthStoreMapper;
import entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

@Service
public class AuthStoreServiceImpl implements AuthStoreService{

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AuthUserService authUserService;

    @Override
    public AuthStoreLoginResponseDTO getStoreByUser(String userLogin) {
        System.out.println(userLogin+"serviceStore");
        Store store = storeRepository.findByUserAccount(authUserService.extractUserlogin(userLogin)).orElseThrow(()->new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        System.out.println(store.getName()+"serviceStore");
        return AuthStoreMapper.INSTANCE.toAuthStoreLoginResponseDto(store);
    }
}
