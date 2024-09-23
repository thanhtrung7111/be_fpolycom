package entity;

import entity.enum_package.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@AttributeOverride(name = "id",column = @Column(name = "userCode"))
@Builder
public class UserAccount extends EntityCommon{

    String userLogin;

    String password;

    @Lob
            @Nationalized
    String name;

    String phone;

    @Lob
    @Nationalized
    String  address;

    @Lob
    String image;

    @Lob
    String bannerImage;

    @Lob
    String email;

    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    UserStatus userStatus;


}
