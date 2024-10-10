package service.administration;

import dao.AdminRepository;
import dto.administration.AdministrationMapper;
import dto.administration.AdministrationRequestDTO;
import dto.administration.AdministrationResponseDTO;
import dto.adminrole.AdminRoleMapper;
import dto.district.DistrictMapper;
import entity.AdminRole;
import entity.Administration;
import entity.District;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdministrationServiceImpl implements AdministrationService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public AdministrationResponseDTO postData(AdministrationRequestDTO administrationRequestDTO) {
        Administration administration = adminRepository.save(AdministrationMapper.INSTANCE.toAdministration(administrationRequestDTO));
        return AdministrationMapper.INSTANCE.toAdministrationResponseDto(administration);
    }

    @Override
    public AdministrationResponseDTO updateData(AdministrationRequestDTO administrationRequestDTO) {
        Administration administration = adminRepository.findById(Long.valueOf(administrationRequestDTO.getAdminCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        administration.setUpdatedDate(new Date());
        administration.setName(administrationRequestDTO.getName());
        administration.setEmail(administrationRequestDTO.getEmail());
        administration.setPhone(administrationRequestDTO.getPhone());
        administration.setUserLogin(administrationRequestDTO.getUserLogin());
        return AdministrationMapper.INSTANCE.toAdministrationResponseDto(adminRepository.save(administration));
    }

    @Override
    public AdministrationResponseDTO deleteData(AdministrationRequestDTO administrationRequestDTO) {
        Administration administration = adminRepository.findById(Long.valueOf(administrationRequestDTO.getAdminCode())).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        administration.setDeleted(true);
        administration.setDeletedDate(new Date());
        return AdministrationMapper.INSTANCE.toAdministrationResponseDto(adminRepository.save(administration));
    }

    @Override
    public List<AdministrationResponseDTO> getAllData() {
        return AdministrationMapper.INSTANCE.toAdministrationList(adminRepository.findAll());
    }

    @Override
    public AdministrationResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
