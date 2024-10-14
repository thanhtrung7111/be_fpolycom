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

    @Mapping(target = "id", source = "storeTransactionCode")
    StoreTransaction toAdminStoreTransaction(AdminWithdrawRequestDTO dto);

    @Mapping(target = "storeTransactionCode", source = "id")
    @Mapping(target = "bankStoreCode", source = "bankStore.id")
    AdminWithdrawResponseDTO adminToAdminWithdrawResponseDTO(StoreTransaction storeTransaction);

    List<AdminWithdrawResponseDTO> toList(List<StoreTransaction> dtoList);
}
