package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenRegister {

    @Id
    String token;


    Date createdDate;

    Date expiredDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userCode",referencedColumnName = "userCode")
    UserAccount userAccount;
}
