package dto.order;

import dto.order_detail.OrderDetailRequestDTO;
import dto.order_detail.OrderDetailResponseDTO;
import dto.voucher.VoucherResponseDTO;
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

    Boolean confirmOrder;

    Boolean confirmDelivery;

    Boolean confirmPickup;

    Double finalTotal;

    String orderBillCode;

    String noteContent;

    String addressDetail;

    String address;

    Long shippingFeeCode;

    Double totalAmountDiscount;

    Long storeCode;

    Long provinceCode;

    Long districtCode;
    Long wardCode;

    Long deliveryTypeCode;


    Long paymentTypeCode;

    Boolean paymentSuccess;

    List<OrderDetailResponseDTO> orderDetailList;

    List<VoucherResponseDTO> voucherList;

}
