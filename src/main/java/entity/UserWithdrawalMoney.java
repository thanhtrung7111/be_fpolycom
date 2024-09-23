package entity;

import entity.enum_package.WithdrawalStatus;
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
public class UserWithdrawalMoney extends EntityCommon{

    Double totalAmount;

    @Lob
    @Nationalized
    String content;

    @Enumerated(EnumType.STRING)
    WithdrawalStatus withdrawalStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankUserCode")
    BankUser bankUser;

    @OneToOne(mappedBy = "userWithdrawalMoney")
    UserWithdrawalReceipt userWithdrawalReceipt;
}
