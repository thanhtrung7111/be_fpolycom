package service.voucher;

import dao.VoucherRepository;
import dto.voucher.VoucherMapper;
import dto.voucher.VoucherResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService{



    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public List<VoucherResponseDTO> getVoucherByStore(Long storeCode) {
        return VoucherMapper.INSTANCE.toVoucherResponseDtoList(voucherRepository.findAllVoucherByStore(storeCode));
    }
}
