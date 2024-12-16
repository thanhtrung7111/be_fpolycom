package dto.relationship;

import entity.Relationship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import javax.management.relation.Relation;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface RelationshipMapper {

    RelationshipMapper INSTANCE = Mappers.getMapper(RelationshipMapper.class);


    @Mapping(target = "userAccountSecondary.id",source = "userCodeSecond")
    Relationship toRelationship(RelationshipRequestDTO relationshipRequestDTO);

    @Mapping(target = "userCode",source = "userAccountSecondary.id")
    @Mapping(target = "username",source = "userAccountSecondary.name")
    @Mapping(target = "image",source = "userAccountSecondary.image")
    @Mapping(target = "status",source = "friendshipStatus")
    RelationshipResponseDTO toRelationshipResponseDto(Relationship relation);

    List<RelationshipResponseDTO> toRelationshipResponseDtoList(List<Relationship> relationList);

}
