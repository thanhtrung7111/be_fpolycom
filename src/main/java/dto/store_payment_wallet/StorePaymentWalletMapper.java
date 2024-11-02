package dto.store_payment_wallet;

import entity.PaymentWalletStore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorePaymentWalletMapper {

    StorePaymentWalletMapper INSTANCE = Mappers.getMapper(StorePaymentWalletMapper.class);

    @Mapping(target = "storeName",source = "store.name")
    @Mapping(target = "storeCode",source = "store.id")
    @Mapping(target = "setPassword",source = "password",qualifiedByName = "setPassword")
    StorePaymentWalletResponseDTO toStorePaymentWalletResponseDto(PaymentWalletStore paymentWalletStore);


    @Named("setPassword")
    default boolean setPassword(String password){
        return password != null && !password.isBlank() ;
    }

}
