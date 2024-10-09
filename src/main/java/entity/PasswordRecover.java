package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@Builder
public class PasswordRecover {

    @Id
    String tokenRecover;


    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime expiredDate;

    @OneToOne
    @JoinColumn(name = "userCode",referencedColumnName = "userCode")
    UserAccount userAccount;
}
