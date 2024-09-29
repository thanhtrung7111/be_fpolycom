package service.province;

import dto.province.BaseProvinceResponseDTO;
import dto.province.ProvinceCreateRequestDTO;
import dto.province.AdminProvinceResponseDTO;
import service.common.CommonService;

import java.util.List;

public interface ProvinceService extends  CommonService<ProvinceCreateRequestDTO, AdminProvinceResponseDTO> {


    public List<BaseProvinceResponseDTO> getAllDataCommon();


}
