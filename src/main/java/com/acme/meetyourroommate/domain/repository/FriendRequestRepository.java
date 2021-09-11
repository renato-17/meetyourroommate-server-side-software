package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.FriendRequest;
import com.acme.meetyourroommate.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    Page<FriendRequest> findByStudentReceived(Student studentReceived, Pageable pageable);
    Page<FriendRequest> findByStudentSend(Student studentSend, Pageable pageable);

    Optional<FriendRequest> findByStudentSendAndStudentReceived(Student studentSend, Student studentReceived);
}
