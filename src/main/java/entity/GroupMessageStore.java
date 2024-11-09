package entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id", column = @Column(name = "messStoreUserCode"))
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class GroupMessageStore extends EntityCommon{

    UserAccount userAccount;

    Store store;
}
