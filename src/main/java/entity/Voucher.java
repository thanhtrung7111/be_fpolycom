package entity;

import entity.enum_package.VoucherType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id", column = @Column(name = "voucherCode"))
public class Voucher extends EntityCommon {

    @Lob
    @Nationalized
    String name;

    Double priceApply;

    Integer percentDecrease;

    @Temporal(TemporalType.DATE)
    Date beginDate;

    @Temporal(TemporalType.DATE)
    Date endDate;

    @Enumerated(EnumType.STRING)
    VoucherType voucherType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    Store store;

    @OneToMany(mappedBy = "voucher")
    List<VoucherUser> voucherUserList;

    @OneToMany(mappedBy = "voucher")
    List<VoucherApply> voucherApplyList;
}
