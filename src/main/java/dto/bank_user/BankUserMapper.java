package dto.bank_user;

import entity.BankUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankUserMapper {

    BankUserMapper INSTANCE = Mappers.getMapper(BankUserMapper.class);


    BankUser toBankUser(BankUserRequestDTO requestDTO);


    List<BankUser> toBankUserList(List<BankUserRequestDTO> requestDTOList);

    @Mapping(target = "bankUserCode",source = "id")
    @Mapping(target = "bankName" ,source = "bankBranch.bank.name")
    @Mapping(target = "bankImage",source = "bankBranch.bank.image")
    @Mapping(target = "bankBranchName",source = "bankBranch.name")
    @Mapping(target = "bankStatus",source = "bankStatus")
    BankUserResponseDTO toBankUserResponseDto(BankUser bankUser);


    List<BankUserResponseDTO> toBankUserResponseDtoList(List<BankUser> bankUserList);
}
