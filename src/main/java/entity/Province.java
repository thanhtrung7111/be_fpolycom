package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "provinceCode"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Province extends EntityCommon {

    @Lob
    @Nationalized
    String name;


    @OneToMany(mappedBy = "province")
    List<District> districtList;

    @OneToMany (mappedBy = "province")
    List<UserAccount> userAccountList;

    @OneToMany(mappedBy = "province")
    List<Store> storeList;

    @OneToMany(mappedBy = "provinceBegin")
    List<ShippingFee> shippingFeeList;

    @OneToMany(mappedBy = "province")
    List<Orders> ordersList;
}
