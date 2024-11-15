package entity;

import entity.enum_package.TypeMessage;
import entity.enum_package.TypeSender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id", column = @Column(name = "messStoreUserCode"))
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class MessageStoreUser extends EntityCommon
{
    String message;

    LocalDateTime timeSend;

    @Enumerated(EnumType.STRING)
    TypeMessage typeMessage;

    Boolean readed;

    Long idSender;

    @Enumerated(EnumType.STRING)
    TypeSender typeSender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupMessageCode")
    GroupMessageStore groupMessageStore;
}
