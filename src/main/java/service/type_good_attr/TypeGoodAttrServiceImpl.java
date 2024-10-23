package service.type_good_attr;

import dao.TypeGoodAttrRepository;

import dto.type_good_attr.TypeGoodAttrMapper;
import dto.type_good_attr.TypeGoodAttrRequestDTO;
import dto.type_good_attr.TypeGoodAttrResponseDTO;
import entity.TypeGood;
import entity.TypeGoodAttr;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TypeGoodAttrServiceImpl implements TypeGoodAttrService {
    @Autowired
    TypeGoodAttrRepository typeGoodAttrRepository;

    @Override
    public TypeGoodAttrResponseDTO postData(TypeGoodAttrRequestDTO typeGoodAttrRequestDTO) {
        TypeGoodAttr typeGoodAttr = typeGoodAttrRepository.save(TypeGoodAttrMapper.INSTANCE.toTypeGoodAttr(typeGoodAttrRequestDTO));
        return TypeGoodAttrMapper.INSTANCE.toTypeGoodAttrResponseDTO(typeGoodAttr);
    }

    @Override
    public TypeGoodAttrResponseDTO updateData(TypeGoodAttrRequestDTO typeGoodAttrRequestDTO) {
        TypeGoodAttr typeGoodAttr = typeGoodAttrRepository.findById(Long.valueOf(typeGoodAttrRequestDTO.getTypeGoodAttrCode())).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        typeGoodAttr.setName(typeGoodAttrRequestDTO.getName());
        typeGoodAttr.setTypeGood(TypeGood.builder().id(Long.valueOf(typeGoodAttrRequestDTO.getTypeGoodCode())).build());
        typeGoodAttr.setUpdatedDate(new Date());
        return TypeGoodAttrMapper.INSTANCE.toTypeGoodAttrResponseDTO(typeGoodAttrRepository.save(typeGoodAttr));
    }

    @Override
    public TypeGoodAttrResponseDTO deleteData(TypeGoodAttrRequestDTO typeGoodAttrRequestDTO) {
        TypeGoodAttr typeGoodAttr = typeGoodAttrRepository.findById(Long.valueOf(typeGoodAttrRequestDTO.getTypeGoodAttrCode())).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        typeGoodAttr.setDeleted(true);
        typeGoodAttr.setDeletedDate(new Date());
        return TypeGoodAttrMapper.INSTANCE.toTypeGoodAttrResponseDTO(typeGoodAttrRepository.save(typeGoodAttr));
    }

    @Override
    public List<TypeGoodAttrResponseDTO> getAllData() {
        return TypeGoodAttrMapper.INSTANCE.toList(typeGoodAttrRepository.findAll());
    }

    @Override
    public TypeGoodAttrResponseDTO getDetailData(Long aLong) {
        return null;
    }

    @Override
    public List<TypeGoodAttrResponseDTO> getAllTypeGoodAttrByTypeGood(Long typeGoodCode) {
        return TypeGoodAttrMapper.INSTANCE.toList(typeGoodAttrRepository.findAllByTypeGood(typeGoodCode));
    }
}
