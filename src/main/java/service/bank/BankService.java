package service.bank;

import dto.bank.BankRequestDTO;
import dto.bank.BankResponseDTO;
import entity.Bank;
import service.common.CommonService;

public interface BankService extends CommonService<BankRequestDTO, BankResponseDTO,Long> {

}
