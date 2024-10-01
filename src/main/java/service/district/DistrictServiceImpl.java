package service.district;

import dao.DistrictRepository;
import dto.district.AdminDistrictRequestDTO;
import dto.district.AdminDistrictResponseDTO;
import dto.district.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService{

    @Autowired
    DistrictRepository districtRepository;
    @Override
    public AdminDistrictResponseDTO postData(AdminDistrictRequestDTO adminDistrictRequestDTO) {
        return null;
    }

    @Override
    public AdminDistrictResponseDTO updateData(AdminDistrictRequestDTO adminDistrictRequestDTO) {
        return null;
    }

    @Override
    public AdminDistrictResponseDTO deleteData(AdminDistrictRequestDTO adminDistrictRequestDTO) {
        return null;
    }

    @Override
    public List<AdminDistrictResponseDTO> getAllData() {
        return DistrictMapper.INSTANCE.toAdminDistrictResponseDtoList(districtRepository.findAll());
    }

    @Override
    public AdminDistrictResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
