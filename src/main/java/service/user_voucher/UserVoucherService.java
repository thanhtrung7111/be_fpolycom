package service.user_voucher;

import dto.voucher_user.VoucherUserRequestDTO;
import dto.voucher_user.VoucherUserResponseDTO;

import java.util.List;

public interface UserVoucherService {

    public List<VoucherUserResponseDTO> getAllVoucherByUser(VoucherUserRequestDTO requestDTO);
    public VoucherUserResponseDTO postUserVoucher(VoucherUserRequestDTO requestDTO);
}
