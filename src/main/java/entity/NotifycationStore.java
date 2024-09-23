package entity;

import entity.enum_package.TypeNotifycationStore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id", column = @Column(name = "notifycationStoreCode"))
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class NotifycationStore extends EntityCommon {

    @Lob
    @Nationalized
    String title;

    @Lob
    @Nationalized
    String content;

    @Lob
    String linkContent;

    @Lob
    String image;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    TypeNotifycationStore typeNotifycationStore;

}
