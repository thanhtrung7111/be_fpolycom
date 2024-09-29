package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id",column = @Column(name = "followedCode"))
public class Followed extends EntityCommon{

    boolean statusFollow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    Store store;

}
