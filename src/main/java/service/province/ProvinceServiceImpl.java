package service.province;

import dao.ProvinceRepository;
import dto.province.AdminProvinceResponseDTO;
import dto.province.BaseProvinceResponseDTO;
import dto.province.ProvinceCreateRequestDTO;
import dto.province.ProvinceMapper;
import entity.Province;
import exeception_handler.DataNotFoundException;
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
        Province saved = provinceRepository.save(ProvinceMapper.INSTANCE.toProvince(provinceCreateRequestDTO));
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(saved);
    }

    @Override
    public AdminProvinceResponseDTO updateData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province province = provinceRepository.findById(Long.valueOf(provinceCreateRequestDTO.getProvinceCode())).orElseThrow(()->new DataNotFoundException("Khong tim thay du lieu"));
        province.setName(provinceCreateRequestDTO.getName());
        province.setUpdatedDate(new Date());
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(provinceRepository.save(province));
    }

    @Override
    public AdminProvinceResponseDTO deleteData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province province = provinceRepository.findById(Long.valueOf(provinceCreateRequestDTO.getProvinceCode())).orElseThrow(()->new DataNotFoundException("Khong tim thay du lieu"));
        province.setDeleted(true);
        province.setDeletedDate(new Date());
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(provinceRepository.save(province));
    }

    @Override
    public List<AdminProvinceResponseDTO> getAllData() {
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDtos(provinceRepository.getAllNotDeleted());
    }

    @Override
    public AdminProvinceResponseDTO getDetailData(Long id) {
        Province province = provinceRepository.findById(id).orElseThrow(()->new DataNotFoundException("Khong ton tai du lieu!"));
        return ProvinceMapper.INSTANCE.toAdminProvinceResponseDto(province);
    }


    public List<BaseProvinceResponseDTO> getAllDataCommon() {
        return ProvinceMapper.INSTANCE.toBaseProvinceResponseDtos(provinceRepository.getAllNotDeleted());
    }
}
