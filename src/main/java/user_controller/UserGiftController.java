package user_controller;

import dto.gift.GiftRequestDTO;
import dto.order.UserOrderRequestDTO;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.gift.GiftService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserGiftController {

    @Autowired
    GiftService giftService;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping(value = "/gift/all")
    public ResponseEntity<Object> getAllGift(@RequestBody Optional<HashMap<String, String>> requestDTO) {
        if (requestDTO.isEmpty() || requestDTO.get().get("userLogin").isBlank()) {
            throw new UsernameNotFoundException("Khong ton tai nguoi dung!");
        }
        return ResponseEntity.ok(dataReturnService.success(giftService.getAllGift(requestDTO.get().get("userLogin"))));
    }


    @PostMapping(value = "/gift/new")
    public ResponseEntity<Object> postNewGift(@RequestBody GiftRequestDTO requestDTO) {
        return ResponseEntity.ok(dataReturnService.success(giftService.postNewGiftResponse(requestDTO)));
    }

    @PostMapping(value = "/gift/detail")
    public ResponseEntity<Object> postNewGift(@RequestBody Optional<HashMap<String, Long>> requestDTO) {
        if(requestDTO.isEmpty()){
            throw new DataNotFoundException("Khong ton tai du lieu!");
        }
        return ResponseEntity.ok(dataReturnService.success(giftService.getGift( requestDTO.get().get("giftCode"))));
    }
}
