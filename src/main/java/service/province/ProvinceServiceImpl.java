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
import java.util.Optional;

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
       if(provinceRepository.existsById(Long.valueOf(provinceCreateRequestDTO.getProvinceCode()))){
           Province province = ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO);
           province.setUpdatedDate(new Date());
           Province provinceSaved = provinceRepository.save(province);
           return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(provinceSaved);
       }
       return null;
    }

    @Override
    public AdminProvinceResponseDTO deleteData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {

        if(provinceRepository.existsById(Long.valueOf(provinceCreateRequestDTO.getProvinceCode()))){
            Province province = ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO);
            Optional<Province> province1 = provinceRepository.findById(Long.valueOf(provinceCreateRequestDTO.getProvinceCode()));
            province.setName(province1.get().getName());
            province.setDeleted(true);
            province.setDeletedDate(new Date());

            return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(provinceRepository.save(province));
        }
      return null;
    }

    @Override
    public List<AdminProvinceResponseDTO> getAllData() {
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDtos(provinceRepository.findAll());
    }

    @Override
    public AdminProvinceResponseDTO getDetailData(Long id) {
        Province province = provinceRepository.findById(id).orElse(null);
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(province);
    }


    public List<BaseProvinceResponseDTO> getAllDataCommon() {
        return ProvinceMapper.INSTANCE.toBaseProvinceResponseDtos(provinceRepository.findAll());
    }
}
