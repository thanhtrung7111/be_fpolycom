package entity;

import entity.enum_package.BankStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "bankStoreCode"))
public class BankStore extends EntityCommon{

    String accountName;

    String accountNumber;

    @Enumerated(EnumType.STRING)
    BankStatus bankStoreStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankBranchCode")
    BankBranch bankBranch;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    Store store;

    @OneToMany(mappedBy = "bankStore")
    List<StoreWithdrawalMoney> storeWithdrawalMoneyList;
}
