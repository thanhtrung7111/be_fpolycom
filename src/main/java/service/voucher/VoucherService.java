package service.voucher;

import dto.voucher.VoucherResponseDTO;

import java.util.List;

public interface VoucherService {

        public List<VoucherResponseDTO> getVoucherByStore(Long storeCode);

}
