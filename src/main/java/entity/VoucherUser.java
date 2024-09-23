package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name="id",column = @Column(name = "voucherUserCode"))
@SuperBuilder
public class VoucherUser extends EntityCommon{

    Boolean apply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucherCode")
    Voucher voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;


}
