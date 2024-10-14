package dto.order;

import dto.order_detail.OrderDetailRequestDTO;
import dto.order_detail.OrderDetailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoResponseDTO {


    String orderCode;
    String userLogin;

    Double totalAmount;

    Double totalAmountVoucher;

    Double totalAmountShip;

    Double finalTotal;

    String orderBillCode;

    String noteContent;

    String addressDetail;

    String address;

    Long shippingFeeCode;

    Long storeCode;

    Long provinceCode;

    Long districtCode;
    Long wardCode;

    Long deliveryTypeCode;


    Long paymentTypeCode;

    Boolean paymentSuccess;

    List<OrderDetailResponseDTO> orderDetailList;

    List<OrderDetailRequestDTO> orderDetailList;
}
