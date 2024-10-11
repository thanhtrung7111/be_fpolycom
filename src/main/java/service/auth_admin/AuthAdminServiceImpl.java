package service.auth_admin;

import dao.AdminRepository;
import dto.auth_admin.AuthAdminMapper;
import dto.auth_admin.AuthAdminResponseDTO;
import entity.Administration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.common.EncodingService;

@Service
public class AuthAdminServiceImpl implements AuthAdminService{

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EncodingService encodingService;

    @Override
    public AuthAdminResponseDTO getAdmin(String userLogin) {
        Administration administration = adminRepository.findByUserLogin(userLogin).orElseThrow(()->new UsernameNotFoundException("Admin khong ton tai"));
        return AuthAdminMapper.INSTANCE.toAuthAdminResponseDto(administration);
    }


    public String getUserLoginAuthenication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();  // Trong một số trường hợp có thể là chuỗi username trực tiếp
            }
        }
        throw new UsernameNotFoundException("Nguoi dung chua xac thuc!");
    }

    @Override
    public String extractUserlogin(String userLogin) {
        return  encodingService.decode(userLogin);
    }

    @Override
    public boolean isValidUserLogin(String rawUserLogin) {
        return extractUserlogin(rawUserLogin).equals(getUserLoginAuthenication());
    }
}
