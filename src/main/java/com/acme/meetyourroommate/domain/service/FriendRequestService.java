package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.FriendRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FriendRequestService {
    Page<FriendRequest> findAllByStudentSendId(Long studentId, Pageable pageable);
    Page<FriendRequest> findAllByStudentReceivedId(Long studentId, Pageable pageable);

    Page<FriendRequest> findAllFriendRequests(Pageable pageable);
    FriendRequest findByStudentSendIdAndStudentReceivedId(Long studentSendId, Long studentReceivedId);

    FriendRequest createFriendRequest(Long studentSendId, Long studentReceivedId);
    FriendRequest updateFriendRequest(Long studentSendId, Long studentReceivedId, FriendRequest newFriendRequest);
    ResponseEntity<?> deleteFriendRequest(Long studentSendId, Long studentReceivedId);

    FriendRequest responseFriendRequest(Long studentSendId, Long studentReceivedId, Integer status);
}
