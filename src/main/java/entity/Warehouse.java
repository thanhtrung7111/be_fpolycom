package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "wareHouseCode"))
public class Warehouse extends EntityCommon {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceCode")
    Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtCode")
    District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wardCode")
    Ward ward;

    String address;

    String addressDetail;

}
