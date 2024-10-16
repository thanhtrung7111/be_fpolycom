package service.ward;

import dto.ward.AdminWardResponseDTO;
import dto.ward.BaseWardResponseDTO;
import dto.ward.WardCreateRequestDTO;
import entity.Ward;
import org.springframework.stereotype.Service;
import service.common.CommonService;

import java.util.List;


public interface WardService extends CommonService<WardCreateRequestDTO,AdminWardResponseDTO,Long> {

    public List<BaseWardResponseDTO> getAllWardByDistrict(Long districtCode);

}
