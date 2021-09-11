package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.FriendRequest;
import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.repository.FriendRequestRepository;
import com.acme.meetyourroommate.domain.repository.StudentRepository;
import com.acme.meetyourroommate.domain.service.FriendRequestService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<FriendRequest> findAllByStudentSendId(Long studentId, Pageable pageable) {
        return studentRepository.findById(studentId).map(
                student -> friendRequestRepository.findByStudentSend(student, pageable)
        ).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public Page<FriendRequest> findAllByStudentReceivedId(Long studentId, Pageable pageable) {
        return studentRepository.findById(studentId).map(
                student -> friendRequestRepository.findByStudentReceived(student, pageable)
        ).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public Page<FriendRequest> findAllFriendRequests(Pageable pageable) {
        return friendRequestRepository.findAll(pageable);
    }

    @Override
    public FriendRequest findByStudentSendIdAndStudentReceivedId(Long studentSendId, Long studentReceivedId) {
        Student studentSend = studentRepository.findById(studentSendId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentSendId));
        Student studentReceived = studentRepository.findById(studentReceivedId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentReceivedId));

        return friendRequestRepository.findByStudentSendAndStudentReceived(studentSend, studentReceived)
                .orElseThrow(() -> new ResourceNotFoundException("Student who send or received not found"));
    }

    @Override
    public FriendRequest createFriendRequest(Long studentSendId, Long studentReceivedId, FriendRequest friendRequest) {
        Student studentSend = studentRepository.findById(studentSendId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentSendId));
        Student studentReceived = studentRepository.findById(studentReceivedId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentReceivedId));

        friendRequest.setStudentSend(studentSend);
        friendRequest.setStudentReceived(studentReceived);
        friendRequest.setStatus(3);
        friendRequest.setStatusDescription(setDescriptionStatus(3));

        return friendRequestRepository.save(friendRequest);
    }

    @Override
    public FriendRequest updateFriendRequest(Long studentSendId, Long studentReceivedId, FriendRequest newFriendRequest) {
        FriendRequest friendRequest = findByStudentSendIdAndStudentReceivedId(studentSendId, studentReceivedId);
        friendRequest.setStatus(newFriendRequest.getStatus());
        friendRequest.setStatusDescription(setDescriptionStatus(friendRequest.getStatus()));
        return friendRequestRepository.save(friendRequest);
    }

    @Override
    public ResponseEntity<?> deleteFriendRequest(Long studentSendId, Long studentReceivedId) {
        FriendRequest friendRequest = findByStudentSendIdAndStudentReceivedId(studentSendId, studentReceivedId);
        friendRequestRepository.delete(friendRequest);
        return ResponseEntity.ok().build();
    }

    private String setDescriptionStatus(Integer status){
        return switch (status) {
            case 1 -> "Accepted";
            case 2 -> "Decline";
            case 3 -> "Pending";
            default -> "Incorrect status";
        };
    }
}
