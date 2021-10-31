package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.FriendRequest;
import com.acme.meetyourroommate.domain.service.FriendRequestService;
import com.acme.meetyourroommate.resource.FriendRequestResource;
import com.acme.meetyourroommate.resource.save.FriendRequestResponse;
import com.acme.meetyourroommate.resource.save.SaveFriendRequestResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FriendRequestController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FriendRequestService friendRequestService;

    @Operation(summary = "Get All Friend Request", description = "Get All Friend Friend Request", tags = {"friend requests"})
    @GetMapping("/friend-requests")
    public Page<FriendRequestResource> getAllFriendRequests(Pageable pageable){
        Page<FriendRequest> friendRequestPage = friendRequestService.findAllFriendRequests(pageable);
        List<FriendRequestResource> resources = friendRequestPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get all Friend Request by Study received Id", description = "Get all Friend Request by the student how receive them", tags = {"friend requests"})
    @GetMapping("/received/friend-requests")
    public Page<FriendRequestResource> getAllFriendRequestByStudentReceived(@RequestParam("student") Long studentId, Pageable pageable){
        Page<FriendRequest> friendRequestPage = friendRequestService.findAllByStudentReceivedId(studentId,pageable);
        List<FriendRequestResource> resources = friendRequestPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get all Friend Request by Study send Id", description = "Get all Friend Request by the student how send them", tags = {"friend requests"})
    @GetMapping("/send/friend-requests")
    public Page<FriendRequestResource> getAllFriendRequestByStudentSend(@RequestParam("student") Long studentId, Pageable pageable){
        Page<FriendRequest> friendRequestPage = friendRequestService.findAllByStudentSendId(studentId,pageable);
        List<FriendRequestResource> resources = friendRequestPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Friend Request", description = "Create an friend request", tags = {"friend requests"})
    @PostMapping("/friend-requests")
    public FriendRequestResource createFriend(@RequestParam("sender") Long studentSend,
                                                        @RequestParam("receiver") Long studentReceived){
        return convertToResource(friendRequestService.createFriendRequest(studentSend,studentReceived));
    }

    @Operation(summary = "Create Friend Request", description = "Create an friend request", tags = {"friend requests"})
    @PutMapping("/friend-requests")
    public FriendRequestResource responseFriendRequest(@RequestParam("sender") Long studentSend,
                                                       @RequestParam("receiver") Long studentReceived,
                                                       @Valid @RequestBody FriendRequestResponse response){
        return convertToResource(friendRequestService.responseFriendRequest(studentSend,studentReceived, response.getStatus()));
    }

    @Operation(summary = "Delete FriendRequest", description = "Delete FriendRequest", tags = {"friend requests"})
    @DeleteMapping("/friend-requests")
    public ResponseEntity<?> deleteFriendRequest(@RequestParam("sender") Long studentSend,
                                                 @RequestParam("receiver") Long studentReceived){
        return friendRequestService.deleteFriendRequest(studentSend,studentReceived);
    }

    private FriendRequest convertToEntity(SaveFriendRequestResource resource) {
        return mapper.map(resource, FriendRequest.class);
    }
    private FriendRequestResource convertToResource(FriendRequest entity) {
        return mapper.map(entity, FriendRequestResource.class);
    }
}
