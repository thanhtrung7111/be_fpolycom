package service;

import dao.UserAccountRepository;
import entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;

import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    UserAccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userDetail = repository.findByUserLogin(username);
        return userDetail.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("USer not found"));
    }



}
