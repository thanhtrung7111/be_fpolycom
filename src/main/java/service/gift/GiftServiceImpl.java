package service.gift;

import dao.GiftRepository;
import dao.OrdersRepository;
import dao.UserAccountRepository;
import dto.gift.GiftMapper;
import dto.gift.GiftRequestDTO;
import dto.gift.GiftResponseDTO;
import entity.Gift;
import entity.Orders;
import entity.UserAccount;
import entity.enum_package.OrderStatus;
import entity.enum_package.TypeOrder;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.auth_user.AuthUserService;

import java.util.Date;
import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    AuthUserService authUserService;
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public List<GiftResponseDTO> getAllGift(String userLogin) {
        if (!authUserService.isValidUserLogin(userLogin)) {
            throw new UsernameNotFoundException("Ban khong co quyen truy cap vao du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(userLogin);
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new UsernameNotFoundException("Khong ton tai nguoi dung!"));
        List<Gift> giftList = giftRepository.findAllGiftByUserLogin(userAccount.getUserLogin());
        return GiftMapper.INSTANCE.toGiftResponseDtoList(giftList);
    }

    @Override
    public GiftResponseDTO postNewGiftResponse(GiftRequestDTO requestDTO) {
        if (!authUserService.isValidUserLogin(requestDTO.getUserLogin())) {
            throw new UsernameNotFoundException("Ban khong co quyen truy cap vao du lieu nay!");
        }
        String userLoginExtract = authUserService.extractUserlogin(requestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(userLoginExtract).orElseThrow(()->new UsernameNotFoundException("Khong ton tai nguoi dung!"));
        UserAccount userAccountGift = userAccountRepository.findById(requestDTO.getUserCode()).orElseThrow(()->new UsernameNotFoundException("Khong ton tai nguoi dung!"));
        Orders orders = ordersRepository.findById(requestDTO.getOrderCode()).orElseThrow(()->new DataNotFoundException("Du lieu khong ton tai"));
        if(orders.getPaymentReceiptList().isEmpty()){
            throw new DataNotFoundException("Don hang chua duoc thanh toan!");
        }
        Gift gift = Gift.builder().orders(orders).userAccount(userAccountGift).createdDate(new Date()).updatedDate(null).deleted(false).deletedDate(null).content(requestDTO.getContent()).build();
        Gift giftSave = giftRepository.save(gift);
        orders.setAddress(userAccountGift.getAddress());
        orders.setAddressDetail(userAccountGift.getAddressDetail());
        orders.setProvince(userAccountGift.getProvince());
        orders.setWard(userAccountGift.getWard());
        orders.setDistrict(userAccountGift.getDistrict());
        orders.setTypeOrder(TypeOrder.gift);
        ordersRepository.save(orders);
        return GiftMapper.INSTANCE.toGiftResponseDto(giftSave);
    }

    @Override
    public GiftResponseDTO getGift(Long giftCode) {
        return GiftMapper.INSTANCE.toGiftResponseDto(giftRepository.findById(giftCode).orElseThrow(()->new DataNotFoundException("Khong tim thay du lieu!")));
    }
}
