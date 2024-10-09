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
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "withdrawalCode"))
public class UserTransaction extends EntityCommon{

    Double totalAmount;

    @Lob
    @Nationalized
    String content;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    TypeTransaction typeTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankUserCode")
    BankUser bankUser;

    @OneToOne(mappedBy = "userTransaction")
    UserTransactionReceipt userTransactionReceipt;
}
