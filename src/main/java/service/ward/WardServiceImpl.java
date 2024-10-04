package service.ward;

import dao.WardRepository;
import dto.ward.AdminWardResponseDTO;
import dto.ward.WardCreateRequestDTO;
import dto.ward.WardMapper;
import entity.District;
import entity.Ward;
import exeception_handler.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WardServiceImpl implements WardService {

    @Autowired
    WardRepository wardRepository;

    @Override
    public AdminWardResponseDTO postData(WardCreateRequestDTO wardCreateRequestDTO) {
        Ward ward = wardRepository.save(WardMapper.INSTANCE.toWard(wardCreateRequestDTO));
        return WardMapper.INSTANCE.adminWardToAdminWardDTO(ward);
    }

    @Override
    public AdminWardResponseDTO updateData(WardCreateRequestDTO wardCreateRequestDTO) {
        Ward ward = wardRepository.findById(Long.valueOf(wardCreateRequestDTO.getWardCode())).orElseThrow(() -> new DataNotFoundException("Khong tim thay du lieu"));
        ward.setName(wardCreateRequestDTO.getName());
        ward.setDistrict(District.builder().id(wardCreateRequestDTO.getWardCode()).build());
        ward.setUpdatedDate(new Date());
        return WardMapper.INSTANCE.adminWardToAdminWardDTO(wardRepository.save(ward));
    }

    @Override
    public AdminWardResponseDTO deleteData(WardCreateRequestDTO wardCreateRequestDTO) {
        Ward ward = wardRepository.findById(Long.valueOf(wardCreateRequestDTO.getWardCode())).orElseThrow(()-> new DataNotFoundException("Khong tim thay du lieu"));
        ward.setDeletedDate(new Date());
        ward.setDeleted(true);
        return WardMapper.INSTANCE.adminWardToAdminWardDTO(wardRepository.save(ward));
    }

    @Override
    public List<AdminWardResponseDTO> getAllData() {
        return WardMapper.INSTANCE.toAdminWardList(wardRepository.findAllNotDeleted());
    }

    @Override
    public AdminWardResponseDTO getDetailData(Long along) {
        return null;
    }
}
