package service;

import dao.AdminRepository;
import entity.Administration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;

import java.util.Optional;

@Service
public class AdministrationService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Administration> administration = adminRepository.findByUserLogin(username);
        return administration.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("Admin not found"));
    }
}
