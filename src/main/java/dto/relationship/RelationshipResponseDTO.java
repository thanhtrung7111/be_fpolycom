package dto.relationship;

import entity.enum_package.FriendshipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipResponseDTO {

    Long userCode;

    String username;

    FriendshipStatus status;


}
