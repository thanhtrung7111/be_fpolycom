package service;

import dao.StoreRepository;
import entity.Store;
import entity.enum_package.StoreStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;
import service.auth_user.AuthUserService;

import java.util.Optional;

@Service
public class StoreService  implements UserDetailsService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    AuthUserService authUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.print(authUserService.extractUserlogin(username));
        Store store =  storeRepository.findByUserAccount(authUserService.extractUserlogin(username)).orElseThrow(()->new UsernameNotFoundException("Cua hang khong ton tai!"));
        if(!store.getStoreStatus().equals(StoreStatus.active)){
            throw new UsernameNotFoundException("Cua hang chua duoc kick hoat!");
        }
        return new UserInfoDetails(store);
    }
}
