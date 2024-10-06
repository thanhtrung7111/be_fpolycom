package entity;

import entity.enum_package.TypeNotifycationUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "notifycationUserCode"))
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotifycationUser extends EntityCommon {

    @Lob
    @Nationalized
    String title;


    @Lob
    @Nationalized
    String content;

    @Lob
    String image;

    @Lob
    String linkContent;

    Boolean readed;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    TypeNotifycationUser typeNotifycation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    UserAccount userAccount;
}
