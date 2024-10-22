package service.type_good_attr;

import dto.type_good_attr.TypeGoodAttrRequestDTO;
import dto.type_good_attr.TypeGoodAttrResponseDTO;
import entity.TypeGoodAttr;
import service.common.CommonService;

import java.util.List;

public interface TypeGoodAttrService extends CommonService<TypeGoodAttrRequestDTO, TypeGoodAttrResponseDTO, Long> {


    public List<TypeGoodAttrResponseDTO> getAllTypeGoodAttrByTypeGood(Long typeGoodCode);

}
