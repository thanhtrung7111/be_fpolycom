package dto.receive_delivery;

import dao.UserAccountRepository;
import dto.import_export_orders.ImportExportOrdersMapper;
import dto.import_export_orders.ImportExportOrdersResponseDTO;
import entity.*;
import entity.enum_package.TypeDelivery;
import org.hibernate.query.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, uses = {UserAccountRepository.class, ImportExportOrdersMapper.class})
public interface ReceiveDeliveryMapper {

    ReceiveDeliveryMapper INSTANCE = Mappers.getMapper(ReceiveDeliveryMapper.class);

    @Mapping(target = "receiveDeliveryCode", source = "id")
    @Mapping(target = "deliveryDate", source = "deliveryDate")
    @Mapping(target = "statusDelivery", source = "statusDelivery")
    @Mapping(target = "orderCode", source = "orders.id")
    @Mapping(target = "receiver", source = "orders.userAccount.district.name")
    @Mapping(target = "address", source = "orders.address")
    ReceiveDeliveryShipperResponse toReceiveShipperResponse(ReceiveDelivery dto);

    @Mapping(target = "receiveDeliveryCode", source = "id")
    @Mapping(target = "deliveryDate", source = "deliveryDate")
    @Mapping(target = "statusDelivery", source = "statusDelivery")
    @Mapping(target = "orderCode", source = "orders.id")
    @Mapping(target = "receiver", source = "orders.userAccount.name")
    @Mapping(target = "address", source = "orders.address")
    ReceiveDeliveryShipperResponse toDeliveryShipperResponse(ReceiveDelivery dto);

    default List<ReceiveDeliveryShipperResponse> toShipperResponseList(List<ReceiveDelivery> dtoList) {
        return dtoList.stream()
                .map(dto -> {
                    if (TypeDelivery.receive.equals(dto.getTypeDelivery())) {
                        return toReceiveShipperResponse(dto);
                    } else if (TypeDelivery.delivery.equals(dto.getTypeDelivery())) {
                        return toDeliveryShipperResponse(dto);
                    }
                    throw new IllegalArgumentException("Invalid typeDelivery: " + dto.getTypeDelivery());
                })
                .collect(Collectors.toList());
    }


    @Mapping(target = "orders.id",source = "ordersCode")
    @Mapping(target = "shipper.id", source = "shipperCode")
    @Mapping(target = "id",source = "receiveDeliveryCode")
    ReceiveDelivery toReceiveDelivery(ReceiveDeliveryRequestDTO dto);

    @Mapping(target ="shipperCode",source = "shipper.id")
    @Mapping(target ="ordersCode",source = "orders.id")
    @Mapping(target = "receiveDeliveryCode", source = "id")
    @Mapping(target = "shipperName", source = "shipper.name")
    @Mapping(target = "shipperPhone", source = "shipper.phone")
    @Mapping(target = "isWarehouse",source = "orders.confirmWarehouse")
    @Mapping(target = "warehouse",source = "orders.importExportOrdersList")
    @Mapping(target = "typePayment",source = "orders.paymentType.name")
    @Mapping(target = "paymentSuccess",source = "orders.paymentReceiptList",qualifiedByName = "paymentSuccesss" +
            "")
    ReceiveDeliveryResponseDTO toReceiveDeliveryResponseDTO(ReceiveDelivery entity);

    List<ReceiveDeliveryResponseDTO> toReceiveDeliveryResponseDtoList(List<ReceiveDelivery> list);

    @Named("paymentSuccesss")
    default  Boolean paymentSuccess (List<PaymentReceipt> paymentReceiptList){
        return paymentReceiptList == null ||  !paymentReceiptList.isEmpty();
    }

}