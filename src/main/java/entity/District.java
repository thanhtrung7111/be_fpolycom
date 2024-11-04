package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id", column = @Column(name = "districtCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class District extends EntityCommon {

    @Lob
    @Nationalized
    String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCode")
    Province province;

    @OneToMany(mappedBy = "district")
    List<Ward> wardList;

    @OneToMany(mappedBy = "district")
    List<UserAccount> userAccountList;

    @OneToMany(mappedBy = "district")
    List<Store> storeList;

    @OneToMany(mappedBy = "district")
    List<Orders> ordersList;

    @OneToMany(mappedBy = "district")
    List<Warehouse> warehouseList;
}
