package service.province;

import dao.ProvinceRepository;
import dto.province.AdminProvinceResponseDTO;
import dto.province.ProvinceCreateRequestDTO;
import dto.province.ProvinceMapper;
import entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;


    @Override
    public AdminProvinceResponseDTO postData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province saved = provinceRepository.save(ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO));
        return ProvinceMapper.INSTANCE.toAdminDTO(saved);
    }

    @Override
    public AdminProvinceResponseDTO updateData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        return null;
    }

    @Override
    public AdminProvinceResponseDTO deleteData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        return null;
    }

    @Override
    public List<AdminProvinceResponseDTO> getAllData() {
        return ProvinceMapper.INSTANCE.toDTOList(provinceRepository.findAll());
    }
}
