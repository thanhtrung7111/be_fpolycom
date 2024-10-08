package user_controller;

import dto.evaluate.UserEvaluateRequestDTO;
import dto.user_cart.UserCartRequestDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.evaluate.EvaluateService;

@RestController
@RequestMapping(value = "/user")
public class UserEvaluateController {

    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    EvaluateService evaluateService;


    @PostMapping(value = "/evaluate/new")
    public ResponseEntity<Object> postUserEvaluate(@RequestBody UserEvaluateRequestDTO requestDTO){
        return ResponseEntity.ok(dataReturnService.success(evaluateService.postUserEvaluateData(requestDTO)));
    }


}
