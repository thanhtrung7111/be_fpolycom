package service.district;

import dto.district.AdminDistrictRequestDTO;
import dto.district.AdminDistrictResponseDTO;
import dto.district.BaseDistrictResponseDTO;
import service.common.CommonService;

import java.util.List;

public interface DistrictService extends CommonService<AdminDistrictRequestDTO, AdminDistrictResponseDTO,Long> {


    public List<BaseDistrictResponseDTO> getAllDistrictByProvince(Long provinceCode);

}
