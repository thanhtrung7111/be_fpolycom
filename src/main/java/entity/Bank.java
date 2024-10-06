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
@AttributeOverride(name="id",column = @Column(name = "bankCode"))
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Bank extends EntityCommon{
    @Lob
    @Nationalized
    String name;


    @Nationalized
    String shortName;

    @Lob
    @Nationalized
    String description;

    @Lob
    String image;

    @OneToMany(mappedBy = "bank")
    List<BankStore> bankStoreList;

    @OneToMany(mappedBy = "bank")
    List<BankUser> bankUserList;

    @OneToMany(mappedBy = "bank")
    List<BankBranch> bankBranchList;


}
