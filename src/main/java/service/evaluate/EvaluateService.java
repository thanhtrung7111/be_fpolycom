package service.evaluate;

import dto.evaluate.UserEvaluateRequestDTO;
import dto.evaluate.UserEvaluateResponseDTO;

import java.util.List;

public interface EvaluateService {


    public UserEvaluateResponseDTO postUserEvaluateData(UserEvaluateRequestDTO requestDTO);


    public List<UserEvaluateResponseDTO> getAllEvaluateProduct(Long productCode);
}
