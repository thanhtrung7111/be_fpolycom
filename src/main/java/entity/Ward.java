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
@AttributeOverride(name = "id", column = @Column(name = "wardCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Ward extends EntityCommon {

    @Lob
    @Nationalized
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtCode")
    District district;

    @OneToMany(mappedBy = "ward")
    List<UserAccount> userAccountList;

    @OneToMany(mappedBy = "ward")
    List<Store> storeList;

    @OneToMany(mappedBy = "ward")
    List<Orders> ordersList;
}
