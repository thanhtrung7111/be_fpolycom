package user_controller;

import dto.relationship.RelationshipRequestDTO;
import dto.relationship.RelationshipResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.data_return.DataReturnService;
import service.relationship.RelationshipService;

import javax.management.relation.RelationService;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/user")
public class UserRelationshipController {

    @Autowired
    RelationshipService relationshipService;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping(value = "/friend/all")
    public ResponseEntity<Object> getAllFriend(@RequestBody HashMap<String, String> request) {
        return ResponseEntity.ok(dataReturnService.success(relationshipService.getAllRelationByUser(request.get("userLogin"))));
    }

    @PostMapping(value = "/friend/all-pending")
    public ResponseEntity<Object> getAllPending(@RequestBody HashMap<String, String> request) {
        return ResponseEntity.ok(dataReturnService.success(relationshipService.getAllRelationByUserPending(request.get("userLogin"))));
    }


    @PostMapping(value = "/friend/request")
    public ResponseEntity<Object> postRequest(@Valid @RequestBody RelationshipRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(relationshipService.postFriendRequest(request)));
    }

    @PostMapping(value = "/friend/accepted")
    public ResponseEntity<Object> postAccept(@Valid @RequestBody RelationshipRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(relationshipService.postFriendAccept(request)));
    }

    @PostMapping(value = "/friend/cancel")
    public ResponseEntity<Object> postDelete(@Valid @RequestBody RelationshipRequestDTO request) {
        return ResponseEntity.ok(dataReturnService.success(relationshipService.postFriendCancel(request)));
    }
}
