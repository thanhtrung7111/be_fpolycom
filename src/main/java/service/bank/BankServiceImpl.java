package service.bank;

import dao.BankRepository;
import dto.bank.BankMapper;
import dto.bank.BankRequestDTO;
import dto.bank.BankResponseDTO;
import entity.Bank;
import exeception_handler.DataNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankRepository bankRepository;

    @Override
    public BankResponseDTO postData(BankRequestDTO bankRequestDTO) {
        Bank bank = bankRepository.save(BankMapper.INSTANCE.toBank(bankRequestDTO));
        return BankMapper.INSTANCE.toBankResponseDTO(bank);
    }

    @Override
    public BankResponseDTO updateData(BankRequestDTO bankRequestDTO) {
        Bank bank = bankRepository.findById(Long.valueOf(bankRequestDTO.getBankCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        bank.setUpdatedDate(new Date());
        bank.setDescription(bankRequestDTO.getDescription());
        bank.setName(bankRequestDTO.getName());
        bank.setImage(bankRequestDTO.getImage());
        bank.setShortName(bankRequestDTO.getShortName());
        return BankMapper.INSTANCE.toBankResponseDTO(bankRepository.save(bank));
    }

    @Override
    public BankResponseDTO deleteData(BankRequestDTO bankRequestDTO) {
        Bank bank = bankRepository.findById(Long.valueOf(bankRequestDTO.getBankCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        bank.setDeleted(true);
        bank.setUpdatedDate(new Date());
        bank.setDeletedDate(new Date());
        return BankMapper.INSTANCE.toBankResponseDTO(bankRepository.save(bank));

    }

    @Override
    public List<BankResponseDTO> getAllData() {

        return BankMapper.INSTANCE.toBankResponseDTO(bankRepository.findAll());
    }

    @Override
    public BankResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
