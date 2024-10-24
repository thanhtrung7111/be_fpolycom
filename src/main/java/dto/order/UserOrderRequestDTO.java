package dto.order;

import dto.order_detail.OrderDetailRequestDTO;
import dto.voucher.VoucherRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequestDTO {

    String userLogin;

    Double totalAmount;

    Double totalAmountVoucher;

    Double totalAmountDiscount;

    Double totalAmountShip;

    Double finalTotal;

    String orderBillCode;

    String noteContent;

    String addressDetail;

    String address;

    Long shippingFeeCode;

    Long storeCode;

    Long provinceCode;

    Long paymentTypeCode;

    Long districtCode;
    Long wardCode;

    Long deliveryTypeCode;

    List<VoucherRequestDTO> voucherList;

    List<OrderDetailRequestDTO> orderDetailList;

}
