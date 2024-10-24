package service;

import dao.ShipperRepository;
import entity.Shipper;
import entity.enum_package.ShipperStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;

@Service
public class ShipperDetailService implements UserDetailsService {
@Autowired
ShipperRepository shipperRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Shipper shipper = shipperRepository.findByUserLogin(username).orElseThrow(() -> new UsernameNotFoundException("Shipper khong ton tai"));
        if(!shipper.getShipperStatus().equals(ShipperStatus.active)){
            throw new UsernameNotFoundException("Shipper chua duoc kich hoat");
        }
        return new UserInfoDetails(shipper);
    }
}
