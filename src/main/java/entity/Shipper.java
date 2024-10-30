package entity;

import entity.enum_package.ShipperStatus;
import entity.enum_package.TypeShipper;
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
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "shipperCode"))
public class Shipper extends EntityCommon{
    @Enumerated(EnumType.STRING)
    TypeShipper typeShipper;

    String userLogin;

    String password;

    @Lob
    @Nationalized
    String name;

    @Lob
    @Nationalized
    String address;

    @Lob
    @Nationalized
    String addressDetail;

    String email;

    String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCode")
    Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtCode")
    District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wardCode")
    Ward ward;

    @Enumerated(EnumType.STRING)
    ShipperStatus shipperStatus;

    @OneToMany(mappedBy = "shipper")
    List<ReceiveDelivery> receiveShippingList;
}
