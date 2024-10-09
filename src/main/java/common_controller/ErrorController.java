package common_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;

@RestController
@CrossOrigin("*")
public class ErrorController {
    @Autowired
    DataReturnService dataReturnService;
    @GetMapping("/error/access-denied")
    public ResponseEntity<Object> errorDenied(){
        return ResponseEntity.ok().body(dataReturnService.authorization());
    }
}
