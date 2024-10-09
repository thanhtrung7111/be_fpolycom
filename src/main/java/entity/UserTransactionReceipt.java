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
@AttributeOverride(name = "id",column = @Column(name = "withdrawalReceiptCode"))
public class UserTransactionReceipt extends EntityCommon{

    Double totalAmount;


    @OneToOne
    @JoinColumn(name = "withdrawalCode",referencedColumnName = "withdrawalCode")
    UserTransaction userTransaction;
}
