package service.voucher;

import dao.VoucherRepository;
import dto.voucher.VoucherMapper;
import dto.voucher.VoucherRequestDTO;
import dto.voucher.VoucherResponseDTO;
import entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.auth_store.AuthStoreService;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService{



    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    AuthStoreService authStoreService;



    @Override
    public List<VoucherResponseDTO> getVoucherByStore(Long storeCode) {

        return VoucherMapper.INSTANCE.toVoucherResponseDtoList(voucherRepository.findAllVoucherByStore(storeCode));
    }

    @Override
    public VoucherResponseDTO postData(VoucherRequestDTO voucherRequestDTO) {
        Voucher voucher = voucherRepository.save(VoucherMapper.INSTANCE.toVoucher(voucherRequestDTO));
        return VoucherMapper.INSTANCE.toVoucherResponseDto(voucher);
    }

    @Override
    public VoucherResponseDTO updateData(VoucherRequestDTO voucherRequestDTO) {
        return null;
    }

    @Override
    public VoucherResponseDTO deleteData(VoucherRequestDTO voucherRequestDTO) {
        return null;
    }

    @Override
    public List<VoucherResponseDTO> getAllData() {
        return List.of();
    }

    @Override
    public VoucherResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
