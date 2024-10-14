package dto.gift;

import dto.order_detail.OrderDetailResponseDTO;
import dto.product.ProductDetailResponseDTO;
import entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftResponseDTO {
    Long userCodeGift;

    Long giftCode;

    Long userCode;
    Date dateGift;
    String usernameGift;

    String username;

    String content;

    String userImage;

    String userImageGift;


    List<OrderDetailResponseDTO> detailOrderList;

}
