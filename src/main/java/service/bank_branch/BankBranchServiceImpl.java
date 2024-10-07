package service.bank_branch;

import dao.BankBranchRepository;
import dto.bank_branch.BankBranchMapper;
import dto.bank_branch.BankBranchRequestDTO;
import dto.bank_branch.BankBranchResponseDTO;
import entity.BankBranch;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankBranchServiceImpl implements BankBranchService {
    @Autowired
    BankBranchRepository bankBranchRepository;

    @Override
    public BankBranchResponseDTO postData(BankBranchRequestDTO bankBranchRequestDTO) {
        BankBranch bankBranch = bankBranchRepository.save(BankBranchMapper.INSTANCE.toBankBranch(bankBranchRequestDTO));
        return BankBranchMapper.INSTANCE.toBankBranchResponseDTO(bankBranch);
    }

    @Override
    public BankBranchResponseDTO updateData(BankBranchRequestDTO bankBranchRequestDTO) {
        BankBranch bankBranch = bankBranchRepository.findById(Long.valueOf(bankBranchRequestDTO.getBankBranchCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        bankBranch.setUpdatedDate(new Date());
        bankBranch.setName(bankBranchRequestDTO.getName());
        return BankBranchMapper.INSTANCE.toBankBranchResponseDTO(bankBranchRepository.save(bankBranch));
    }

    @Override
    public BankBranchResponseDTO deleteData(BankBranchRequestDTO bankBranchRequestDTO) {
        BankBranch bankBranch = bankBranchRepository.findById(Long.valueOf(bankBranchRequestDTO.getBankCode())).orElseThrow(()->new DataNotFoundException("data not found"));
        bankBranch.setDeleted(true);
        bankBranch.setUpdatedDate(new Date());
        bankBranch.setDeletedDate(new Date());
        return BankBranchMapper.INSTANCE.toBankBranchResponseDTO(bankBranchRepository.save(bankBranch));
    }

    @Override
    public List<BankBranchResponseDTO> getAllData() {
        return BankBranchMapper.INSTANCE.toBankBranchResponseDTO(bankBranchRepository.findAll());
    }

    @Override
    public BankBranchResponseDTO getDetailData(Long aLong) {
        return null;
    }}






