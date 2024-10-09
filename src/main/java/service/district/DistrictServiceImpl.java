package service.district;

import dao.DistrictRepository;
import dto.district.AdminDistrictRequestDTO;
import dto.district.AdminDistrictResponseDTO;
import dto.district.DistrictMapper;
import entity.District;
import entity.Province;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService{

    @Autowired
    DistrictRepository districtRepository;
    @Override
    public AdminDistrictResponseDTO postData(AdminDistrictRequestDTO adminDistrictRequestDTO) {
        District district = districtRepository.save(DistrictMapper.INSTANCE.toDistrict(adminDistrictRequestDTO));
        return DistrictMapper.INSTANCE.toAdminDistrictResponseDto(district);
    }

    @Override
    public AdminDistrictResponseDTO updateData(AdminDistrictRequestDTO adminDistrictRequestDTO) {
        District district = districtRepository.findById(adminDistrictRequestDTO.getDistrictCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        district.setName(adminDistrictRequestDTO.getName());
        district.setProvince(Province.builder().id(adminDistrictRequestDTO.getProvinceCode()).build());
        district.setUpdatedDate(new Date());
        return DistrictMapper.INSTANCE.toAdminDistrictResponseDto(districtRepository.save(district));
    }

    @Override
    public AdminDistrictResponseDTO deleteData(AdminDistrictRequestDTO adminDistrictRequestDTO) {
        District district = districtRepository.findById(adminDistrictRequestDTO.getDistrictCode()).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        district.setDeleted(true);
        district.setDeletedDate(new Date());
        return DistrictMapper.INSTANCE.toAdminDistrictResponseDto(districtRepository.save(district));
    }

    @Override
    public List<AdminDistrictResponseDTO> getAllData() {
        return DistrictMapper.INSTANCE.toAdminDistrictResponseDtoList(districtRepository.findAllNotDeleted());
    }

    @Override
    public AdminDistrictResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
