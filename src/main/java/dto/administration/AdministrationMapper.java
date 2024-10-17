package dto.administration;

import dto.bank.BankMapper;
import dto.bank.BankRequestDTO;
import entity.Administration;
import entity.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface AdministrationMapper {
    AdministrationMapper INSTANCE = Mappers.getMapper(AdministrationMapper.class);
    @Mapping(target = "id" , source = "adminCode")
    Administration toAdministration(AdministrationRequestDTO dto);

    @Mapping(target = "adminCode",source = "id")
    AdministrationResponseDTO  toAdministrationResponseDto(Administration administration);

    List<AdministrationResponseDTO > toAdministrationList(List<Administration> administrations);

}
