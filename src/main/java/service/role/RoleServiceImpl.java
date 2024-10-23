package service.role;

import dao.RoleRepository;
import dto.bank.BankMapper;
import dto.role_admin.RoleMapper;
import dto.role_admin.RoleRequestDTO;
import dto.role_admin.RoleResponseDTO;
import entity.Bank;
import entity.Role;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleResponseDTO postData(RoleRequestDTO roleRequestDTO) {
        Role role = roleRepository.save(RoleMapper.INSTANCE.toRole(roleRequestDTO));
        return RoleMapper.INSTANCE.toRoleResponseDto(role);
    }

    @Override
    public RoleResponseDTO updateData(RoleRequestDTO roleRequestDTO) {
        Role role = roleRepository.findById(Long.valueOf(roleRequestDTO.getRoleCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        role.setUpdatedDate(new Date());
        role.setName(roleRequestDTO.getName());
        role.setRightCode(roleRequestDTO.getRightCode());
        return RoleMapper.INSTANCE.toRoleResponseDto(roleRepository.save(role));

    }

    @Override
    public RoleResponseDTO deleteData(RoleRequestDTO roleRequestDTO) {
        Role role = roleRepository.findById(Long.valueOf(roleRequestDTO.getRoleCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        role.setDeleted(true);
        role.setUpdatedDate(new Date());
        role.setDeletedDate(new Date());
        return RoleMapper.INSTANCE.toRoleResponseDto(roleRepository.save(role));
    }

    @Override
    public List<RoleResponseDTO> getAllData() {
        return RoleMapper.INSTANCE.toRoleResponseDTO(roleRepository.findAll());
    }

    @Override
    public RoleResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
