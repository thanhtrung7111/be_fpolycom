package dto.order;

import dto.order_detail.OrderDetailMapper;
import dto.receive_delivery.ConsignorResponseDTO;
import dto.receive_delivery.ReceiveDeliveryMapper;
import dto.receive_delivery.ReceiveDeliveryRequestDTO;
import dto.receive_delivery.RecieverResponseDTO;
import dto.voucher.VoucherMapper;
import dto.voucher.VoucherRequestDTO;
import entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,uses = {OrderDetailMapper.class, VoucherMapper.class, ReceiveDeliveryMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "orderCode",source = "id")
    @Mapping(target = "storeImage",source = "store.image")
    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "deliveryType",source = "deliveryType.name")
    @Mapping(target = "paymentTypeName",source = "paymentType.name")
    @Mapping(target = "orderDate",source = "createdDate")
    @Mapping(target = "paymentSuccess",source = "paymentReceiptList",qualifiedByName = "paymentSuccess")
    OrderResponseDTO toOrderResponseDto(Orders order);


    @Mapping(target = "shippingFee.id",source ="shippingFeeCode" )
    @Mapping(target = "province.id",source = "provinceCode")
    @Mapping(target = "ward.id",source = "wardCode")
    @Mapping(target = "district.id",source = "districtCode")
    @Mapping(target = "deliveryType.id",source = "deliveryTypeCode")
    @Mapping(target = "orderDetailList",source = "orderDetailList")
    @Mapping(target = "store.id",source = "storeCode")
    @Mapping(target = "paymentType.id",source = "paymentTypeCode")
    @Mapping(target = "voucherApplyList",source = "voucherList",qualifiedByName = "toVoucherList")
    Orders toOrders (UserOrderRequestDTO requestDTO);


    @Mapping(target = "shippingFeeCode",source ="shippingFee.id" )
    @Mapping(target = "provinceCode",source = "province.id")
    @Mapping(target = "districtCode",source = "district.id")
    @Mapping(target = "wardCode",source = "ward.id")
    @Mapping(target = "deliveryTypeCode",source = "deliveryType.id")
    @Mapping(target = "orderDetailList",source = "orderDetailList")
    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "orderCode",source = "id")
    @Mapping(target = "paymentTypeCode",source = "paymentType.id")
    @Mapping(target = "paymentSuccess",source = "paymentReceiptList",qualifiedByName = "paymentSuccess")
    @Mapping(target = "receiveDeliveryList",source = "receiveDeliveryList")
    @Mapping(target = "orderDate",source = "createdDate")
    @Mapping(target = "voucherList",source = "voucherApplyList",qualifiedByName = "voucherList")
    OrderInfoResponseDTO toOrderInfoResponseDto (Orders orders);


    @Mapping(target = "orderCode",source = "id")
    @Mapping(target = "orderDate",source = "createdDate")
    @Mapping(target = "shippingFee",source ="shippingFee.fee" )
    @Mapping(target = "deliveryType",source = "deliveryType.name")
    @Mapping(target = "storeInfo",source = "store", qualifiedByName = "mapConsignorResponseDTO")
    @Mapping(target = "paymentType",source = "paymentType.name")
    @Mapping(target = "paymentSuccess",source = "paymentReceiptList",qualifiedByName = "paymentSuccess")
    @Mapping(target = "userAccountInfo",source = "userAccount", qualifiedByName = "mapReceiverResponseDTO")
    @Mapping(target = "orderDetailShipperList",source = "orderDetailList")
    @Mapping(target = "receiveDeliveryList",source = "receiveDeliveryList")
    OrderShipperResponseDTO toOrderShipperResponseDTO (Orders orders);

    List<OrderResponseDTO> toOrderResponseDtoList(List<Orders> ordersList);

    List<OrderInfoResponseDTO> toOrderInfoResponseDtoList(List<Orders> ordersList);


    List<Orders> toOrdersList(List<UserOrderRequestDTO> orderRequestDTOList);

    List<Orders> toOrdersListForReiceiveDtos(List<ReceiveDeliveryRequestDTO> list);

//    List<Orders> toOrdersLists(List<Orders> list);

    @Named("mapConsignorResponseDTO")
    @Mapping(target = "id",             source = "id")
    @Mapping(target = "ward",           source = "ward.name")
    @Mapping(target = "district",       source = "district.name")
    @Mapping(target = "province",       source = "province.name")
    ConsignorResponseDTO mapConsignorResponseDTO(Store store);

    @Named("mapReceiverResponseDTO")
    @Mapping(target = "ward",           source = "ward.name")
    @Mapping(target = "district",       source = "district.name")
    @Mapping(target = "province",       source = "province.name")
    RecieverResponseDTO mapReceiverResponseDTO(UserAccount userAccount);

    @Named("paymentSuccess")
    default  Boolean paymentSuccess (List<PaymentReceipt> paymentReceiptList){
        return paymentReceiptList == null ||  !paymentReceiptList.isEmpty();
    }
    @Named("voucherList")
    default  List<Voucher> voucherList (List<VoucherApply> voucherApplyList){
        return voucherApplyList.stream().map(item->item.getVoucher()).collect(Collectors.toList());
    }

    @Named("toVoucherList")
    default  List<VoucherApply> toVoucherList (List<VoucherRequestDTO> voucherApplyList){
        List<VoucherApply> voucherApplies = new ArrayList<>();
        voucherApplyList.forEach(item->{
            VoucherApply voucherApply = VoucherApply.builder().voucher(Voucher.builder().id(item.getVoucherCode()).build()).build();
            voucherApplies.add(voucherApply);
        });
        return voucherApplies;
    }


}
