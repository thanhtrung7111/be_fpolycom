package store_controller;

import dto.store_productSale.ProductsSaleRequestDTO;
import entity.enum_package.ProductStatus;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.store_productsSale.ProductsSaleService;

import java.util.HashMap;

@RestController
@RequestMapping(value="/top-product")
public class ProductSaleController {
    @Autowired
    DataReturnService dataReturnService;
    @Autowired
    ProductsSaleService productsSaleService;

    @PostMapping(value="/topsale")
    public ResponseEntity<Object> getTopsale(@RequestBody ProductsSaleRequestDTO productsSaleRequestDTO) {
        if( productsSaleRequestDTO.getStoreCode() == null || productsSaleRequestDTO.getTypeGoodName()==null){
            throw new DataNotFoundException("Không đe trong ma cua hang hoac ma loai hang");
        }
        return ResponseEntity.ok(dataReturnService.success(productsSaleService.productTopSelling(Long.valueOf(productsSaleRequestDTO.getStoreCode()), productsSaleRequestDTO.getTypeGoodName())));
    }
}
