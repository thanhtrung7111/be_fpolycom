package service.province;

import dao.ProvinceRepository;
import dto.province.ProvinceCreateRequestDTO;
import dto.province.ProvinceMapper;
import dto.province.ProvinceResponseDTO;
import entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.common.CommonService;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;


    @Override
    public ProvinceResponseDTO postData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        Province saved = provinceRepository.save(ProvinceMapper.INSTANCE.toEnity(provinceCreateRequestDTO));
        return ProvinceMapper.INSTANCE.toDTO(saved);
    }

    @Override
    public ProvinceResponseDTO updateData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        return null;
    }

    @Override
    public ProvinceResponseDTO deleteData(ProvinceCreateRequestDTO provinceCreateRequestDTO) {
        return null;
    }

    @Override
    public List<ProvinceResponseDTO> getAllData() {
        return ProvinceMapper.INSTANCE.toDTOList(provinceRepository.findAll());
    }
}
