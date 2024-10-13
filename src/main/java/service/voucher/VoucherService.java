package service.voucher;

import dto.voucher.VoucherRequestDTO;
import dto.voucher.VoucherResponseDTO;
import service.common.CommonService;

import java.util.List;

public interface VoucherService extends CommonService<VoucherRequestDTO, VoucherResponseDTO, Long> {

        public List<VoucherResponseDTO> getVoucherByStore(Long storeCode);

}
