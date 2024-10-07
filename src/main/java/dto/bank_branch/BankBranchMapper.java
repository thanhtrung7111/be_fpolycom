package dto.bank_branch;


import entity.Bank;
import entity.BankBranch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface BankBranchMapper {
    BankBranchMapper INSTANCE = Mappers.getMapper(BankBranchMapper.class);

    @Mapping(target = "id", source = "bankBranchCode")
    @Mapping(target = "bank.id", source = "bankCode")
    BankBranch toBankBranch(BankBranchRequestDTO dto);

    @Mapping(target = "bankBranchCode", source = "id")
    @Mapping(target = "bankCode", source = "bank.id")
    BankBranchResponseDTO toBankBranchResponseDTO(BankBranch bankBranch);

    List<BankBranchResponseDTO> toBankBranchResponseDTO(List<BankBranch> bankBranchs);
}