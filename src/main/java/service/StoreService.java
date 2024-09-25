package service;

import dao.StoreRepository;
import entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;

import java.util.Optional;

@Service
public class StoreService  implements UserDetailsService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Store> store =  storeRepository.findByUserAccount(username);
        return store.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("Cua hang khong ton tai"));
    }
}
