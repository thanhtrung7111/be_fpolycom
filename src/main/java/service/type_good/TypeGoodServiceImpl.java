package service.type_good;

import dao.TypeGoodRepository;
import dto.district.DistrictMapper;
import dto.type_good.TypeGoodMapper;
import dto.type_good.TypeGoodRequestDTO;
import dto.type_good.TypeGoodResponseDTO;
import entity.District;
import entity.Province;
import entity.TypeGood;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TypeGoodServiceImpl implements TypeGoodService {
@Autowired
TypeGoodRepository typeGoodRepository;

    @Override
    public TypeGoodResponseDTO postData(TypeGoodRequestDTO typeGoodRequestDTO) {
        TypeGood typeGood = typeGoodRepository.save(TypeGoodMapper.INSTANCE.toTypeGood(typeGoodRequestDTO));
        return TypeGoodMapper.INSTANCE.toTypeGoodResponseDto(typeGood);
    }

    @Override
    public TypeGoodResponseDTO updateData(TypeGoodRequestDTO typeGoodRequestDTO) {
        TypeGood typeGood = typeGoodRepository.findById(Long.valueOf(typeGoodRequestDTO.getTypeGoodCode())).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        typeGood.setName(typeGoodRequestDTO.getName());
        typeGood.setUpdatedDate(new Date());
        typeGood.setImage(typeGoodRequestDTO.getImage());
        return TypeGoodMapper.INSTANCE.toTypeGoodResponseDto(typeGoodRepository.save(typeGood));
    }

    @Override
    public TypeGoodResponseDTO deleteData(TypeGoodRequestDTO typeGoodRequestDTO) {
        TypeGood typeGood = typeGoodRepository.findById(Long.valueOf(typeGoodRequestDTO.getTypeGoodCode())).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        typeGood.setDeleted(true);
        typeGood.setDeletedDate(new Date());
        return TypeGoodMapper.INSTANCE.toTypeGoodResponseDto(typeGoodRepository.save(typeGood));
    }

    @Override
    public List<TypeGoodResponseDTO> getAllData() {
        return TypeGoodMapper.INSTANCE.toList(typeGoodRepository.findAll());
    }

    @Override
    public TypeGoodResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
