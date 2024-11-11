package dto.user_notify;

import entity.enum_package.TypeNotifycationUser;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotifycationResponseDTO {

    String title;

Long notifyUserCode;

    String content;


    String image;


    String linkContent;

    Boolean readed;

    @Enumerated(EnumType.STRING)
    TypeNotifycationUser typeNotifycation;

}
