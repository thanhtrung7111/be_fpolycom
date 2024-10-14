package service.gift;

import dto.gift.GiftRequestDTO;
import dto.gift.GiftResponseDTO;

import java.util.List;

public interface GiftService {



    public List<GiftResponseDTO> getAllGift(String userLogin);

    public GiftResponseDTO postNewGiftResponse(GiftRequestDTO requestDTO);


    public GiftResponseDTO getGift(Long giftCode);

}
