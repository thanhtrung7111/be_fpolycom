package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "paymentWalletCode"))
public class PaymentWalletUser extends EntityCommon{

    Double balance;

    String password;

    @OneToOne
    @JoinColumn(name="userCode",referencedColumnName = "userCode")
    UserAccount userAccount;

}
