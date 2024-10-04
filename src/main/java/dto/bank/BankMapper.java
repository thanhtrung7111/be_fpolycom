package dto.bank;

import entity.Bank;
import entity.enum_package.BankBranch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    @Mapping(target = "id" , source = "bankCode")
    Bank toBank(BankRequestDTO dto);

    @Mapping(target = "bankCode",source = "id")
    @Mapping(target = "numberOfBankBranch",source = "bankBranchList", qualifiedByName = "Branchs")
    BankResponseDTO toBankResponseDTO(Bank bank);

    List<BankResponseDTO> toBankResponseDTO(List<Bank> banks);

    @Named("Branchs")
    default Integer toBankBranch(List<BankBranch> branchs) {
        if(branchs == null){
            return 0;
        }
        return  branchs.size();
    }
}
