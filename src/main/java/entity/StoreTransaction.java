package entity;

import entity.enum_package.TransactionStatus;
import entity.enum_package.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id", column = @Column(name = "withdrawalCode"))
public class StoreTransaction extends EntityCommon {

    Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Lob
    TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    TypeTransaction typeTransaction;

    @Lob
    @Nationalized
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankStoreCode")
    BankStore bankStore;

    @OneToOne(mappedBy = "storeTransaction")
    StoreTransactionReceipt storeTransactionReceipt;
}
