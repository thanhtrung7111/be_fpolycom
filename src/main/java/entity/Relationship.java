package entity;

import entity.enum_package.FriendshipStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "relationshipCode"))
public class Relationship extends EntityCommon {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCodePrimary")
    UserAccount userAccountPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCodeSecondary")
    UserAccount userAccountSecondary;

    FriendshipStatus friendshipStatus;
}
