package admin_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import service.province.ProvinceService;

@RestController
@CrossOrigin("*")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;


}