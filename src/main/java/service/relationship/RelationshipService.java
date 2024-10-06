package service.relationship;

import dto.relationship.RelationshipRequestDTO;
import dto.relationship.RelationshipResponseDTO;
import service.common.CommonService;

import java.util.List;

public interface RelationshipService extends CommonService<RelationshipRequestDTO, RelationshipResponseDTO,Long> {


    public List<RelationshipResponseDTO> getAllRelationByUser(String userLogin);

    public List<RelationshipResponseDTO> getAllRelationByUserPending(String userLogin);

    public RelationshipResponseDTO postFriendRequest(RelationshipRequestDTO requestDTO);

    public RelationshipResponseDTO postFriendAccept(RelationshipRequestDTO requestDTO);

    public RelationshipResponseDTO postFriendCancel(RelationshipRequestDTO requestDTO);
}
