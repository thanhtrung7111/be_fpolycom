package dto.store_transaction;

import dto.voucher.VoucherMapper;
import entity.StoreTransaction;
import entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreTransactionMapper {
    StoreTransactionMapper INSTANCE = Mappers.getMapper(StoreTransactionMapper.class);

    @Mapping(target = "id",source = "storeTransactionCode")
    @Mapping(target = "bankStore.id", source = "bankStoreCode")
    StoreTransaction toStoreTransaction(WithdrawTransactionRequestDTO dto);


    @Mapping(target = "storeTransactionCode", source = "id")
    @Mapping(target = "bankStoreCode", source = "bankStore.id")
    WithdrawTransactionResponseDTO toWithdrawTransactionResponseDTO(StoreTransaction storeTransaction);

    @Mapping(target = "storeTransactionCode", source = "id")
    @Mapping(target = "bankStoreCode", source = "bankStore.id")
    @Mapping(target = "bankAccountName", source = "bankStore.accountName")
    @Mapping(target = "bankAccountNumber", source = "bankStore.accountNumber")
    @Mapping(target = "bankBranchName", source = "bankStore.bankBranch.name")
    @Mapping(target = "bankBranchCode", source = "bankStore.bankBranch.id")
    @Mapping(target = "bankName", source = "bankStore.bankBranch.bank.shortName")
    TransactionDetailResponseDTO toTransactionDetailResponseDto(StoreTransaction storeTransaction);

    @Mapping(target = "id", source = "storeTransactionCode")
    StoreTransaction toAdminStoreTransaction(AdminWithdrawRequestDTO dto);

    @Mapping(target = "storeTransactionCode", source = "id")
    @Mapping(target = "bankStoreCode", source = "bankStore.id")
    @Mapping(target = "bankAccountName", source = "bankStore.accountName")
    @Mapping(target = "bankAccountNumber", source = "bankStore.accountNumber")
    @Mapping(target = "bankBranchName", source = "bankStore.bankBranch.name")
    @Mapping(target = "bankName", source = "bankStore.bankBranch.bank.shortName")
    @Mapping(target = "storeCode", source = "bankStore.store.id")
    @Mapping(target = "storeName", source = "bankStore.store.name")
    AdminWithdrawResponseDTO adminToAdminWithdrawResponseDTO(StoreTransaction storeTransaction);

    List<AdminWithdrawResponseDTO> toList(List<StoreTransaction> dtoList);


    List<TransactionDetailResponseDTO> toTransactionDetailResponseDtoList(List<StoreTransaction> dtoList);
}
