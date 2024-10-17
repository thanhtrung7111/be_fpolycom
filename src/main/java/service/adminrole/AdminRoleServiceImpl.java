package service.adminrole;

import dao.AdminRoleRepository;
import dto.adminrole.AdminRoleMapper;
import dto.adminrole.AdminRoleRequestDTO;
import dto.adminrole.AdminRoleResponseDTO;
import dto.role_admin.RoleMapper;
import entity.AdminRole;
import entity.Role;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
     AdminRoleRepository adminRoleRepository;

    @Override
    public AdminRoleResponseDTO postData(AdminRoleRequestDTO adminRoleRequestDTO) {
        AdminRole adminrole = adminRoleRepository.save(AdminRoleMapper.INSTANCE.toEntity(adminRoleRequestDTO));
        return AdminRoleMapper.INSTANCE.toAdminRoleResponseDto(adminrole);
    }

    @Override
    public AdminRoleResponseDTO updateData(AdminRoleRequestDTO adminRoleRequestDTO) {
        AdminRole adminrole = adminRoleRepository.findById(Integer.valueOf(adminRoleRequestDTO.getAdminRoleCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        adminrole.setUpdatedDate(new Date());
        adminrole.setRole(adminrole.getRole());
        return AdminRoleMapper.INSTANCE.toAdminRoleResponseDto(adminRoleRepository.save(adminrole));
    }

    @Override
    public AdminRoleResponseDTO deleteData(AdminRoleRequestDTO adminRoleRequestDTO) {
        return null;
    }

    @Override
    public List<AdminRoleResponseDTO> getAllData() {
        return AdminRoleMapper.INSTANCE.toAdminRoleResponseDtoList(adminRoleRepository.findAll());
    }

    @Override
    public AdminRoleResponseDTO getDetailData(Integer integer) {
        return null;
    }
}
