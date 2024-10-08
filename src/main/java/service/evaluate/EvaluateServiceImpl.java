package service.evaluate;

import dao.EvaluateRepository;
import dao.ProductRepository;
import dao.UserAccountRepository;
import dto.evaluate.EvaluateMapper;
import dto.evaluate.UserEvaluateRequestDTO;
import dto.evaluate.UserEvaluateResponseDTO;
import entity.Evaluate;
import entity.Product;
import entity.UserAccount;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;

@Service

public class EvaluateServiceImpl implements EvaluateService{

    @Autowired
    EvaluateRepository evaluateRepository;


    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ProductRepository productRepository;



    @Override
    @Transactional
    public UserEvaluateResponseDTO postUserEvaluateData(UserEvaluateRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen tren du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new DataNotFoundException("Du lieu khog  ton tai!"));

        Product product = productRepository.findById(requestDTO.getProductCode()).orElseThrow(()->new DataNotFoundException("San pham khong ton tai"));
        Evaluate evaluate = EvaluateMapper.INSTANCE.toEvaluate(requestDTO);
        evaluate.setUserAccount(userAccount);
        evaluate.setProduct(product);
        evaluate.setCreatedDate(new Date());
        System.out.println(evaluate.getEvaluateImageList().size());
        evaluate.getEvaluateImageList().forEach(item->item.setEvaluate(evaluate));
        evaluateRepository.save(evaluate);
        return EvaluateMapper.INSTANCE.toUserEvaluateResponseDto(evaluate);
    }

    @Override
    public List<UserEvaluateResponseDTO> getAllEvaluateProduct(Long productCode) {
        List<Evaluate> evaluateList  =evaluateRepository.findEvaluateByProduct(productCode);
        for(Evaluate item : evaluateList){
            System.out.println(item.getEvaluateImageList().size());
//            System.out.println(item.getEvaluateImageList().get(1).getImage());
        }
        return EvaluateMapper.INSTANCE.toUserEvaluateResponseDtoList(evaluateList);
    }
}
