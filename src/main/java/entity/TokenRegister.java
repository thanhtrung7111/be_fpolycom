package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class TokenRegister {

    @Id
    String token;


    Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime expiredDate;

    @OneToOne
    @JoinColumn(name="userCode",referencedColumnName = "userCode")
    UserAccount userAccount;
}
