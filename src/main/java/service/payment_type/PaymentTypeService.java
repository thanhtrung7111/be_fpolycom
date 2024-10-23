package service.payment_type;

import dto.payment_type.PaymentTypeRequestDTO;
import dto.payment_type.PaymentTypeResponseDTO;
import service.common.CommonService;

public interface PaymentTypeService extends CommonService<PaymentTypeRequestDTO, PaymentTypeResponseDTO,Long> {
}
