package store_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;

@RestController
@RequestMapping("/store")
public class VoucherController {
    @Autowired
    DataReturnService dataReturnService;


}
