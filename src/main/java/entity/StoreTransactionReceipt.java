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
@AttributeOverride(name = "id",column = @Column(name = "withdrawalReceiptCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class StoreTransactionReceipt extends  EntityCommon{

    Double totalAmount;

    @OneToOne
    @JoinColumn(name="withdrawalCode",referencedColumnName = "withdrawalCode")
    StoreTransaction storeTransaction;


}
