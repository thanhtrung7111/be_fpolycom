package service.user_bank;

import dto.bank_user.BankUserRequestDTO;
import dto.bank_user.BankUserResponseDTO;

import java.util.List;

public interface UserBankService {


    public List<BankUserResponseDTO> getAllBankByUser(BankUserRequestDTO requestDTO);

    public BankUserResponseDTO postBankUser(BankUserRequestDTO requestDTO);




}
