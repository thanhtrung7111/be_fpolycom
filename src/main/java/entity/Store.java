package entity;

import entity.enum_package.StoreStatus;
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
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "storeCode"))
public class Store extends EntityCommon {

    @Lob
    @Nationalized
    String name;

    @Lob
    String image;

    @Lob
    @Nationalized
    String addressDetail;

    @Lob
    @Nationalized
    String address;

    String phone;

    String password;

    @Lob
    String bannerImage;

    StoreStatus storeStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userCode",referencedColumnName = "userCode")
    UserAccount userAccount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCode")
    Province province;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtCode")
    District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wardCode")
    Ward ward;


    @OneToMany(mappedBy = "store")
    List<StoreDocument> storeDocumentList;

    @OneToMany(mappedBy = "store")
    List<BankStore> bankStoreList;

}
