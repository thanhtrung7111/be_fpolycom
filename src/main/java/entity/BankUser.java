package entity;

import entity.enum_package.BankStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id", column = @Column(name = "bankUserCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class BankUser extends EntityCommon {


    String accountNumber;

    String accountName;

    @Enumerated(EnumType.STRING)
    BankStatus bankStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankBranchCode")
    BankBranch bankBranch;

    @OneToMany(mappedBy = "bankUser")
    List<UserTransaction> userTransactionList;
}
