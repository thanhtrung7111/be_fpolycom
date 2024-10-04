package entity.enum_package;

import entity.Bank;
import entity.EntityCommon;
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
@AttributeOverride(name="id",column = @Column(name = "bankBranchCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class BankBranch extends EntityCommon {
    @Nationalized
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankCode")
    Bank bank;


}
