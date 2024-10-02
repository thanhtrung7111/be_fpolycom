package service.province;

import dao.ProvinceRepository;
import dto.province.AdminProvinceResponseDTO;
import dto.province.BaseProvinceResponseDTO;
import dto.province.ProvinceCreateRequestDTO;
import dto.province.ProvinceMapper;
import entity.Province;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;


    @Override
    public AdminProvinceResponseDTO postData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province saved = provinceRepository.save(ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO));
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(saved);
    }

    @Override
    public AdminProvinceResponseDTO updateData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province province = ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO);
        Province provinceSaved = provinceRepository.save(province);
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(provinceSaved);
    }

    @Override
    public AdminProvinceResponseDTO deleteData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province province = ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO);
        province.setDeleted(true);
        province.setDeletedDate(new Date());
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(provinceRepository.save(province));
    }

    @Override
    public List<AdminProvinceResponseDTO> getAllData() {
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDtos(provinceRepository.findAll());
    }

    @Override
    public AdminProvinceResponseDTO getDetailData(Long aLong) {
        return null;
    }

    public List<BaseProvinceResponseDTO> getAllDataCommon() {
        return ProvinceMapper.INSTANCE.toBaseProvinceResponseDtos(provinceRepository.findAll());
    }
}
