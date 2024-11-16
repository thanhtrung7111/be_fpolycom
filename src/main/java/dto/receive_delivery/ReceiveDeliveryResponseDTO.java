package dto.receive_delivery;

import dto.import_export_orders.ImportExportOrdersResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveDeliveryResponseDTO {
    Long receiveDeliveryCode;
    String image;
    Date deliveryDate;
    String typeDelivery;
    String statusDelivery;
    Long shipperCode;
    String shipperName;
    Long ordersCode;

    String shipperPhone;

    Boolean isWarehouse;
    List<ImportExportOrdersResponseDTO> warehouse;
    Boolean paymentSuccess;

    String typePayment;
}
